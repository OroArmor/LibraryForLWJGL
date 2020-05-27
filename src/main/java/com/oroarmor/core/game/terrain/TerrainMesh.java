package com.oroarmor.core.game.terrain;

import java.lang.Thread.State;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.VertexBufferLayout;
import com.oroarmor.util.VectorUtils;

public class TerrainMesh {

	private static class TerrainMeshData {
		public IntBuffer tris;
		public FloatBuffer verticies;

		public TerrainMeshData(final FloatBuffer verticies, final IntBuffer tris) {
			this.verticies = verticies;
			this.tris = tris;
		}
	}

	private static int currentThreads = 0;
	private static int MAX_THREADS = 5;

	public static float maxHeight = 100;

	private static VertexBufferLayout terrainVbo = new VertexBufferLayout();

	static {
		terrainVbo.pushFloats(3);
		terrainVbo.pushFloats(3);
		terrainVbo.pushFloats(1);
	}
	private float[][] heightMap;

	private Mesh mesh;

	private TerrainMeshData meshData = null;
	private final FloatBuffer meshDataFloatBuffer;

	private final IntBuffer meshDataIntBuffer;
	Runnable meshGenRunnable = () -> {
		currentThreads++;
		TerrainMesh.this.heightMap = TerrainNoiseGenerator.generateNoiseMap(TerrainMesh.this.width, TerrainMesh.this.height, new Vector2f(TerrainMesh.this.x, TerrainMesh.this.y));
		final TerrainMeshData tempData = TerrainMesh.this.generateMeshData(TerrainMesh.this.heightMap);
		TerrainMesh.this.setMeshData(tempData);
		currentThreads--;
	};

	Thread meshGenThread = new Thread(this.meshGenRunnable);

	int width, height;

	float x, y;

	public TerrainMesh(final int width, final int height, final float x, final float y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;

		this.meshDataFloatBuffer = BufferUtils.createFloatBuffer(6 * 7 * width * height);
		this.meshDataIntBuffer = BufferUtils.createIntBuffer(6 * width * height);
	}

	private TerrainMeshData generateMeshData(final float[][] generatedNoiseMap) {

		final float min = maxHeight * 0.25f;

		int triangleCount = 0;

		for (int i = 0; i < this.width - 1; i++) {
			for (int j = 0; j < this.height - 1; j++) {

				final float y00 = Math.max((maxHeight + min) * generatedNoiseMap[i][j], min) - min;
				final float y10 = Math.max((maxHeight + min) * generatedNoiseMap[i + 1][j], min) - min;
				final float y11 = Math.max((maxHeight + min) * generatedNoiseMap[i + 1][j + 1], min) - min;
				final float y01 = Math.max((maxHeight + min) * generatedNoiseMap[i][j + 1], min) - min;

				final float t1h = (y00 + y10 + y11) / 3;
				final float t2h = (y00 + y01 + y11) / 3;

				final Vector3f p1 = new Vector3f(0 + i, y00, 0 + j);
				final Vector3f p2 = new Vector3f(1 + i, y10, 0 + j);
				final Vector3f p3 = new Vector3f(1 + i, y11, 1 + j);
				final Vector3f p4 = new Vector3f(0 + i, y01, 1 + j);

				final Vector3f n1 = p1.sub(p2, new Vector3f()).cross(p3.sub(p2, new Vector3f())).negate();
				final Vector3f n2 = p1.sub(p4, new Vector3f()).cross(p3.sub(p4, new Vector3f()));

				// There needs to be a better way to do this... maybe a mesh generator class
				// that does the calculations automatically

				this.meshDataFloatBuffer.put(VectorUtils.vectorToArray(p1));
				this.meshDataFloatBuffer.put(VectorUtils.vectorToArray(n1));
				this.meshDataFloatBuffer.put(t1h);

				this.meshDataFloatBuffer.put(VectorUtils.vectorToArray(p2));
				this.meshDataFloatBuffer.put(VectorUtils.vectorToArray(n1));
				this.meshDataFloatBuffer.put(t1h);

				this.meshDataFloatBuffer.put(VectorUtils.vectorToArray(p3));
				this.meshDataFloatBuffer.put(VectorUtils.vectorToArray(n1));
				this.meshDataFloatBuffer.put(t1h);

				this.meshDataFloatBuffer.put(VectorUtils.vectorToArray(p1));
				this.meshDataFloatBuffer.put(VectorUtils.vectorToArray(n2));
				this.meshDataFloatBuffer.put(t2h);

				this.meshDataFloatBuffer.put(VectorUtils.vectorToArray(p3));
				this.meshDataFloatBuffer.put(VectorUtils.vectorToArray(n2));
				this.meshDataFloatBuffer.put(t2h);

				this.meshDataFloatBuffer.put(VectorUtils.vectorToArray(p4));
				this.meshDataFloatBuffer.put(VectorUtils.vectorToArray(n2));
				this.meshDataFloatBuffer.put(t2h);

				for (int k = 0; k < 6; k++) {
					this.meshDataIntBuffer.put(triangleCount++);
				}

			}
		}

		this.meshDataFloatBuffer.flip();
		this.meshDataIntBuffer.flip();

		return new TerrainMeshData(this.meshDataFloatBuffer, this.meshDataIntBuffer);
	}

	public float[][] getHeightMap() {
		return this.heightMap;
	}

	public Mesh getMesh() {
		if (this.getMeshData() == null && this.meshGenThread.getState() == State.NEW) {
			if (currentThreads < MAX_THREADS) {
				this.meshGenThread.start();
			}
		}

		if (this.mesh == null && this.getMeshData() != null) {
			this.mesh = new Mesh(this.getMeshData().verticies, this.getMeshData().tris, terrainVbo);
		}

		return this.mesh;
	}

	private TerrainMeshData getMeshData() {
		return this.meshData;
	}

	private void setMeshData(final TerrainMeshData data) {
		this.meshData = data;
	}

}
