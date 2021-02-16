package com.oroarmor.core.game.terrain;

import java.lang.Thread.State;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.VertexBufferLayout;
import com.oroarmor.util.VectorUtils;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

public class TerrainMesh {
    private static class TerrainMeshData {
        public IntBuffer tris;
        public FloatBuffer vertices;

        public TerrainMeshData(FloatBuffer vertices, IntBuffer tris) {
            this.vertices = vertices;
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
        TerrainMesh.this.heightMap = TerrainNoiseGenerator.generateNoiseMap(TerrainMesh.this.width,
                TerrainMesh.this.height, new Vector2f(TerrainMesh.this.x, TerrainMesh.this.y));
        final TerrainMeshData tempData = TerrainMesh.this.generateMeshData(TerrainMesh.this.heightMap);
        TerrainMesh.this.setMeshData(tempData);
        currentThreads--;
    };

    Thread meshGenThread = new Thread(meshGenRunnable);

    int width, height;

    float x, y;

    public TerrainMesh(int width, int height, float x, float y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;

        meshDataFloatBuffer = BufferUtils.createFloatBuffer(6 * 7 * width * height);
        meshDataIntBuffer = BufferUtils.createIntBuffer(6 * width * height);
    }

    private TerrainMeshData generateMeshData(float[][] generatedNoiseMap) {
        float min = maxHeight * 0.25f;

        int triangleCount = 0;

        for (int i = 0; i < width - 1; i++) {
            for (int j = 0; j < height - 1; j++) {

                float y00 = Math.max((maxHeight + min) * generatedNoiseMap[i][j], min) - min;
                float y10 = Math.max((maxHeight + min) * generatedNoiseMap[i + 1][j], min) - min;
                float y11 = Math.max((maxHeight + min) * generatedNoiseMap[i + 1][j + 1], min) - min;
                float y01 = Math.max((maxHeight + min) * generatedNoiseMap[i][j + 1], min) - min;

                float t1h = (y00 + y10 + y11) / 3;
                float t2h = (y00 + y01 + y11) / 3;

                Vector3f p1 = new Vector3f(i, y00, j);
                Vector3f p2 = new Vector3f(1 + i, y10, j);
                Vector3f p3 = new Vector3f(1 + i, y11, 1 + j);
                Vector3f p4 = new Vector3f(i, y01, 1 + j);

                Vector3f n1 = p1.sub(p2, new Vector3f()).cross(p3.sub(p2, new Vector3f())).negate();
                Vector3f n2 = p1.sub(p4, new Vector3f()).cross(p3.sub(p4, new Vector3f()));

                // There needs to be a better way to do this... maybe a mesh generator class
                // that does the calculations automatically

                meshDataFloatBuffer.put(VectorUtils.vectorToArray(p1));
                meshDataFloatBuffer.put(VectorUtils.vectorToArray(n1));
                meshDataFloatBuffer.put(t1h);

                meshDataFloatBuffer.put(VectorUtils.vectorToArray(p2));
                meshDataFloatBuffer.put(VectorUtils.vectorToArray(n1));
                meshDataFloatBuffer.put(t1h);

                meshDataFloatBuffer.put(VectorUtils.vectorToArray(p3));
                meshDataFloatBuffer.put(VectorUtils.vectorToArray(n1));
                meshDataFloatBuffer.put(t1h);

                meshDataFloatBuffer.put(VectorUtils.vectorToArray(p1));
                meshDataFloatBuffer.put(VectorUtils.vectorToArray(n2));
                meshDataFloatBuffer.put(t2h);

                meshDataFloatBuffer.put(VectorUtils.vectorToArray(p3));
                meshDataFloatBuffer.put(VectorUtils.vectorToArray(n2));
                meshDataFloatBuffer.put(t2h);

                meshDataFloatBuffer.put(VectorUtils.vectorToArray(p4));
                meshDataFloatBuffer.put(VectorUtils.vectorToArray(n2));
                meshDataFloatBuffer.put(t2h);

                for (int k = 0; k < 6; k++) {
                    meshDataIntBuffer.put(triangleCount++);
                }

            }
        }

        meshDataFloatBuffer.flip();
        meshDataIntBuffer.flip();

        return new TerrainMeshData(meshDataFloatBuffer, meshDataIntBuffer);
    }

    public float[][] getHeightMap() {
        return heightMap;
    }

    public Mesh getMesh() {
        if (getMeshData() == null && meshGenThread.getState() == State.NEW) {
            if (currentThreads < MAX_THREADS) {
                meshGenThread.start();
            }
        }

        if (mesh == null && getMeshData() != null) {
            mesh = new Mesh(getMeshData().vertices, getMeshData().tris, terrainVbo);
        }

        return mesh;
    }

    private TerrainMeshData getMeshData() {
        return meshData;
    }

    private void setMeshData(TerrainMeshData data) {
        meshData = data;
    }
}
