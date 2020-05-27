package com.oroarmor.util;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;

import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.VertexBufferLayout;

/**
 * This file loads wavefront object files. (.obj)
 *
 * @author OroArmor
 *
 */
public class OBJLoader {

	public static final int VERTICIES_PER_FACE = 3;
	public static final int UV_COORD_LENGTH = 2;
	public static final int NORMAL_COORD_LENGTH = 3;
	public static final int POSITION_COORD_LENGTH = 3;

	/**
	 * This is the {@link VertexBufferLayout} for object files. 3 coords for
	 * position, 2 for texture, and 3 for normals.
	 */
	public static VertexBufferLayout objLayout = new VertexBufferLayout() //
			.pushFloats(POSITION_COORD_LENGTH) //
			.pushFloats(UV_COORD_LENGTH) //
			.pushFloats(NORMAL_COORD_LENGTH);

	/**
	 * <strong> --WARNING -- </strong> <br>
	 * Do not pass in the file path, pass in the data from the file
	 *
	 * @param data The data of the object file
	 * @return A mesh with the data from the object file
	 */
	public static Mesh loadOBJ(final String data) {

		final int vertexCount = StringUtils.countOf("v", data);
		final int uvCount = StringUtils.countOf("vt", data);
		final int normalCount = StringUtils.countOf("vn", data);
		final int faceCount = StringUtils.countOf("f", data);

		final String[] fileData = data.split("\\n");

		// x, y, x
		final FloatBuffer verticies = BufferUtils.createFloatBuffer(vertexCount * POSITION_COORD_LENGTH);
		// u, v
		final FloatBuffer uv = BufferUtils.createFloatBuffer(uvCount * UV_COORD_LENGTH);
		// nx, ny, nz
		final FloatBuffer normals = BufferUtils.createFloatBuffer(normalCount * NORMAL_COORD_LENGTH);

		// x, y, z, u, v, nx, ny, nz
		final FloatBuffer meshData = BufferUtils.createFloatBuffer(
				faceCount * VERTICIES_PER_FACE * (POSITION_COORD_LENGTH + UV_COORD_LENGTH + NORMAL_COORD_LENGTH));
		final IntBuffer triangles = BufferUtils.createIntBuffer(faceCount * VERTICIES_PER_FACE);

		final HashMap<String, Integer> triangleNames = new HashMap<>();

		int vertexCounts = 0;

		for (final String line : fileData) {
			final String[] tokens = line.split(" ");

			final String token = tokens[0];

			switch (token) {
			case "v":
				verticies.put(Float.parseFloat(tokens[1]));
				verticies.put(Float.parseFloat(tokens[2]));
				verticies.put(Float.parseFloat(tokens[3]));
				break;
			case "vt":
				uv.put(Float.parseFloat(tokens[1]));
				uv.put(Float.parseFloat(tokens[2]));
				break;
			case "vn":
				normals.put(Float.parseFloat(tokens[1]));
				normals.put(Float.parseFloat(tokens[2]));
				normals.put(Float.parseFloat(tokens[3]));
				break;
			case "f":
				for (int i = 1; i < tokens.length; i++) {
					if (triangleNames.containsKey(tokens[i])) {
						triangles.put(triangleNames.get(tokens[i]));
					} else {
						final String[] vtxUvNorm = tokens[i].split("/");
						final int vertexID = Integer.parseInt(vtxUvNorm[0]) - 1;
						final int uvID = Integer.parseInt(vtxUvNorm[1]) - 1;
						final int normID = Integer.parseInt(vtxUvNorm[2].trim()) - 1;

						meshData.put(verticies.get(vertexID * POSITION_COORD_LENGTH));
						meshData.put(verticies.get(vertexID * POSITION_COORD_LENGTH + 1));
						meshData.put(verticies.get(vertexID * POSITION_COORD_LENGTH + 2));

						meshData.put(uv.get(uvID * UV_COORD_LENGTH));
						meshData.put(uv.get(uvID * UV_COORD_LENGTH + 1));

						meshData.put(normals.get(normID * NORMAL_COORD_LENGTH));
						meshData.put(normals.get(normID * NORMAL_COORD_LENGTH + 1));
						meshData.put(normals.get(normID * NORMAL_COORD_LENGTH + 2));

						triangles.put(vertexCounts);
						triangleNames.put(tokens[i], vertexCounts);
						vertexCounts++;
					}
				}
			}
		}

		meshData.flip();
		triangles.flip();

		return new Mesh(meshData, triangles, objLayout);
	}

	/**
	 * Loads an OBJ from a file
	 *
	 * @param filePath The path to the file
	 * @return A mesh with the OBJ data
	 */
	public static Mesh loadOBJFromFile(final String filePath) {
		return loadOBJ(ResourceLoader.loadFile(filePath));
	}

	/**
	 * No instances for you
	 */
	private OBJLoader() {
	}

}
