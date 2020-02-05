package com.oroarmor.core.game.terrain;

import java.util.ArrayList;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.VertexBufferLayout;

@SuppressWarnings("unused")
public class TerrainMesh {

	private static VertexBufferLayout terrainVbo = new VertexBufferLayout();
	static {
		terrainVbo.pushFloats(3);
		terrainVbo.pushFloats(3);
	}

	private Mesh mesh;
	private float[][] heightMap;
	private int[] triangles;
	private float[] vertexData;

	int width, height;

	public TerrainMesh(int width, int height, float x, float y) {
		this.width = width;
		this.height = height;
		this.heightMap = TerrainNoiseGenerator.generateNoiseMap(width, height, new Vector2f(x, y));
		this.mesh = generateMeshData(heightMap);
	}

	private Mesh generateMeshData(float[][] generatedNoiseMap) {
		ArrayList<Float> tempVertexData = new ArrayList<Float>();
		ArrayList<Integer> tempTriangles = new ArrayList<Integer>();

		int triangleCount = 0;

		for (int i = 0; i < width - 1; i++) {
			for (int j = 0; j < height - 1; j++) {

				float y00 = 30 * generatedNoiseMap[i][j];
				float y10 = 30 * generatedNoiseMap[i + 1][j];
				float y11 = 30 * generatedNoiseMap[i + 1][j + 1];
				float y01 = 30 * generatedNoiseMap[i][j + 1];

				Vector3f p1 = new Vector3f(0 + i, y00, 0 + j);
				Vector3f p2 = new Vector3f(1 + i, y10, 0 + j);
				Vector3f p3 = new Vector3f(1 + i, y11, 1 + j);
				Vector3f p4 = new Vector3f(0 + i, y01, 1 + j);

				Vector3f n1 = p1.sub(p2, new Vector3f()).cross(p3.sub(p2, new Vector3f())).negate();
				Vector3f n2 = p1.sub(p4, new Vector3f()).cross(p3.sub(p4, new Vector3f()));

				// There needs to be a better way to do this... maybe a mesh generator class
				// that does the calculations automatically

				tempVertexData.add(p1.x);
				tempVertexData.add(p1.y);
				tempVertexData.add(p1.z);
				tempVertexData.add(n1.x);
				tempVertexData.add(n1.y);
				tempVertexData.add(n1.z);

				tempVertexData.add(p2.x);
				tempVertexData.add(p2.y);
				tempVertexData.add(p2.z);
				tempVertexData.add(n1.x);
				tempVertexData.add(n1.y);
				tempVertexData.add(n1.z);

				tempVertexData.add(p3.x);
				tempVertexData.add(p3.y);
				tempVertexData.add(p3.z);
				tempVertexData.add(n1.x);
				tempVertexData.add(n1.y);
				tempVertexData.add(n1.z);

				tempVertexData.add(p1.x);
				tempVertexData.add(p1.y);
				tempVertexData.add(p1.z);
				tempVertexData.add(n2.x);
				tempVertexData.add(n2.y);
				tempVertexData.add(n2.z);

				tempVertexData.add(p3.x);
				tempVertexData.add(p3.y);
				tempVertexData.add(p3.z);
				tempVertexData.add(n2.x);
				tempVertexData.add(n2.y);
				tempVertexData.add(n2.z);

				tempVertexData.add(p4.x);
				tempVertexData.add(p4.y);
				tempVertexData.add(p4.z);
				tempVertexData.add(n2.x);
				tempVertexData.add(n2.y);
				tempVertexData.add(n2.z);

				for (int k = 0; k < 6; k++)
					tempTriangles.add(triangleCount++);

			}
		}

		Float[] meshDataArrayG = new Float[tempVertexData.size()];
		float[] meshDataArray = new float[tempVertexData.size()];
		Integer[] triangleArrayG = new Integer[tempTriangles.size()];
		int[] triangleArray = new int[tempTriangles.size()];
		tempVertexData.toArray(meshDataArrayG);
		tempTriangles.toArray(triangleArrayG);

		for (int i = 0; i < meshDataArray.length; i++) {
			meshDataArray[i] = meshDataArrayG[i].floatValue();
		}

		for (int i = 0; i < triangleArray.length; i++) {
			triangleArray[i] = triangleArrayG[i].intValue();
		}

		this.vertexData = meshDataArray;
		this.triangles = triangleArray;

		return new Mesh(meshDataArray, triangleArray, terrainVbo);
	}

	public Mesh getMesh() {
		return mesh;
	}

}
