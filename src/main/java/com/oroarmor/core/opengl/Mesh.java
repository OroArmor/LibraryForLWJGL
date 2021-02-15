package com.oroarmor.core.opengl;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * A {@link Mesh} stores a {@link IndexBufferObject} and a
 * {@link VertexArrayObject} and can be rendered easily
 *
 * @author OroArmor
 */
public class Mesh {

    /**
     * The {@link IndexBufferObject} of the mesh
     */
    private final IndexBufferObject ibo;

    /**
     * The {@link VertexArrayObject} of the mesh
     */
    private final VertexArrayObject vao;

    /**
     * Constructs a basic mesh based on the input data
     *
     * @param vertexPositions The vertex data of the mesh
     * @param indexPositions  The index data of the mesh
     * @param layout          The vertex layout for the mesh
     */
    public Mesh(final float[] vertexPositions, final int[] indexPositions, final VertexBufferLayout layout) {
        final VertexBufferObject vbo = new VertexBufferObject(vertexPositions);
        vao = new VertexArrayObject();
        vao.addBuffer(vbo, layout);

        ibo = new IndexBufferObject(indexPositions);
    }

    /**
     * Constructs a basic mesh based on the input data
     *
     * @param vertexPositions The vertex data of the mesh
     * @param indexPositions  The index data of the mesh
     * @param layout          The vertex layout for the mesh
     */
    public Mesh(final FloatBuffer vertexPositions, final IntBuffer indexPositions, final VertexBufferLayout layout) {
        final VertexBufferObject vbo = new VertexBufferObject(vertexPositions);
        vao = new VertexArrayObject();
        vao.addBuffer(vbo, layout);

        ibo = new IndexBufferObject(indexPositions);
    }

    /**
     * Renders the mesh with the {@code renderer} and the {@code shader}
     *
     * @param renderer The {@link Renderer} to use
     * @param shader   The {@link Shader} to use
     */
    public void render(final Renderer renderer, final Shader shader) {
        renderer.draw(vao, ibo, shader);
    }

}
