package com.oroarmor.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.VertexBufferLayout;

/**
 * This file loads wavefront object files. (.obj)
 * 
 * @author OroArmor
 *
 */
public class OBJLoader {

	/**
	 * No instances for you
	 */
	private OBJLoader() {
	};

	/**
	 * This is the {@link VertexBufferLayout} for object files. 3 coords for
	 * position, 2 for texture, and 3 for normals.
	 */
	public static VertexBufferLayout objLayout = new VertexBufferLayout();

	static {
		objLayout.pushFloats(3);
		objLayout.pushFloats(2);
		objLayout.pushFloats(3);
	}

	/**
	 * 
	 * @param filePath The path to the object file
	 * @return A mesh with the data from the object file
	 */
	public static Mesh loadOBJ(String filePath) {
		String[] fileData = ResourceLoader.loadFile(filePath).split("\\n");

		// x, y, x
		ArrayList<Float> verticies = new ArrayList<Float>();
		// u, v
		ArrayList<Float> uv = new ArrayList<Float>();
		// nx, ny, nz
		ArrayList<Float> normals = new ArrayList<Float>();

		// x, y, z, u, v, nx, ny, nz
		ArrayList<Float> meshData = new ArrayList<Float>();
		ArrayList<Integer> triangles = new ArrayList<Integer>();

		HashMap<String, Integer> triangleNames = new HashMap<String, Integer>();

		int vertexCounts = 0;

		for (String line : fileData) {
			String[] tokens = line.split(" ");

			String token = tokens[0];

			switch (token) {
			case "v":
				verticies.add(Float.parseFloat(tokens[1]));
				verticies.add(Float.parseFloat(tokens[2]));
				verticies.add(Float.parseFloat(tokens[3]));
				break;
			case "vt":
				uv.add(Float.parseFloat(tokens[1]));
				uv.add(Float.parseFloat(tokens[2]));
				break;
			case "vn":
				normals.add(Float.parseFloat(tokens[1]));
				normals.add(Float.parseFloat(tokens[2]));
				normals.add(Float.parseFloat(tokens[3]));
				break;
			case "f":
				for (int i = 1; i < tokens.length; i++) {
//					if (triangleNames.containsKey(tokens[i])) {
//						triangles.add(triangleNames.get(tokens[i]));
//					} else {
					String[] vtxUvNorm = tokens[i].split("/");
					int vertexID = Integer.parseInt(vtxUvNorm[0]) - 1;
					int uvID = Integer.parseInt(vtxUvNorm[1]) - 1;
					int normID = Integer.parseInt(vtxUvNorm[2]) - 1;

					meshData.add(verticies.get(vertexID * 3));
					meshData.add(verticies.get(vertexID * 3 + 1));
					meshData.add(verticies.get(vertexID * 3 + 2));

					meshData.add(uv.get(uvID * 2));
					meshData.add(uv.get(uvID * 2 + 1));

					meshData.add(normals.get(normID * 3));
					meshData.add(normals.get(normID * 3 + 1));
					meshData.add(normals.get(normID * 3 + 2));

					triangles.add(vertexCounts);
					triangleNames.put(tokens[i], vertexCounts);
					vertexCounts++;
//					}
				}
			}
		}

		Float[] meshDataArrayG = new Float[meshData.size()];
		float[] meshDataArray = new float[meshData.size()];
		Integer[] triangleArrayG = new Integer[triangles.size()];
		int[] triangleArray = new int[triangles.size()];
		meshData.toArray(meshDataArrayG);
		triangles.toArray(triangleArrayG);

		for (int i = 0; i < meshDataArray.length; i++) {
			meshDataArray[i] = meshDataArrayG[i].floatValue();
		}

		for (int i = 0; i < triangleArray.length; i++) {
			triangleArray[i] = triangleArrayG[i].intValue();
		}

		return new Mesh(meshDataArray, triangleArray, objLayout);
	}

}
