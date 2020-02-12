package com.oroarmor.core.game.terrain;

import java.lang.Thread.State;
import java.util.ArrayList;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.VertexBufferLayout;

public class TerrainMesh {

	private static class MeshData {
		public int[] tris;
		public float[] verticies;

		public MeshData(float[] verticies, int[] tris) {
			this.verticies = verticies;
			this.tris = tris;
		}
	}
	public static float maxHeight = 200;

	private static VertexBufferLayout terrainVbo = new VertexBufferLayout();

	static {
		terrainVbo.pushFloats(3);
		terrainVbo.pushFloats(3);
		terrainVbo.pushFloats(1);
	}

	private float[][] heightMap;
	private Mesh mesh;

	private MeshData meshData;
	Runnable meshGenRunnable = new Runnable() {
		@Override
		public void run() {
			long millis = System.currentTimeMillis();
			heightMap = TerrainNoiseGenerator.generateNoiseMap(width, height, new Vector2f(x, y));
			MeshData tempData = generateMeshData(heightMap);
			setMeshData(tempData);
			System.out.println(x + " " + y + " time: " + (System.currentTimeMillis() - millis));

		}
	};

	Thread meshGenThread = new Thread(meshGenRunnable);

	int width, height;

	float x, y;

	public TerrainMesh(int width, int height, float x, float y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;

	}

	private MeshData generateMeshData(float[][] generatedNoiseMap) {
		ArrayList<Float> tempVertexData = new ArrayList<Float>();
		ArrayList<Integer> tempTriangles = new ArrayList<Integer>();

		float min = 100;

		int triangleCount = 0;

		for (int i = 0; i < width - 1; i++) {
			for (int j = 0; j < height - 1; j++) {

				float y00 = Math.max((maxHeight + min) * generatedNoiseMap[i][j], min) - min;
				float y10 = Math.max((maxHeight + min) * generatedNoiseMap[i + 1][j], min) - min;
				float y11 = Math.max((maxHeight + min) * generatedNoiseMap[i + 1][j + 1], min) - min;
				float y01 = Math.max((maxHeight + min) * generatedNoiseMap[i][j + 1], min) - min;

				float t1h = (y00 + y10 + y11) / 3;
				float t2h = (y00 + y01 + y11) / 3;

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
				tempVertexData.add(t1h);

				tempVertexData.add(p2.x);
				tempVertexData.add(p2.y);
				tempVertexData.add(p2.z);
				tempVertexData.add(n1.x);
				tempVertexData.add(n1.y);
				tempVertexData.add(n1.z);
				tempVertexData.add(t1h);

				tempVertexData.add(p3.x);
				tempVertexData.add(p3.y);
				tempVertexData.add(p3.z);
				tempVertexData.add(n1.x);
				tempVertexData.add(n1.y);
				tempVertexData.add(n1.z);
				tempVertexData.add(t1h);

				tempVertexData.add(p1.x);
				tempVertexData.add(p1.y);
				tempVertexData.add(p1.z);
				tempVertexData.add(n2.x);
				tempVertexData.add(n2.y);
				tempVertexData.add(n2.z);
				tempVertexData.add(t2h);

				tempVertexData.add(p3.x);
				tempVertexData.add(p3.y);
				tempVertexData.add(p3.z);
				tempVertexData.add(n2.x);
				tempVertexData.add(n2.y);
				tempVertexData.add(n2.z);
				tempVertexData.add(t2h);

				tempVertexData.add(p4.x);
				tempVertexData.add(p4.y);
				tempVertexData.add(p4.z);
				tempVertexData.add(n2.x);
				tempVertexData.add(n2.y);
				tempVertexData.add(n2.z);
				tempVertexData.add(t2h);

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

		return new MeshData(meshDataArray, triangleArray);
	}

	public Mesh getMesh() {
		if (getMeshData() == null && meshGenThread.getState() == State.NEW) {
			meshGenThread.start();
		}

		if (mesh == null && getMeshData() != null) {

			mesh = new Mesh(getMeshData().verticies, getMeshData().tris, terrainVbo);
		}

		return mesh;
	}

	private synchronized MeshData getMeshData() {
		return meshData;
	}

	private synchronized void setMeshData(MeshData data) {
		this.meshData = data;
	}

}
