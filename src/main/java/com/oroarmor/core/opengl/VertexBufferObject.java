package com.oroarmor.core.opengl;

import java.nio.FloatBuffer;

import com.oroarmor.core.Bindable;
import com.oroarmor.core.Destructor;

import static org.lwjgl.opengl.GL15.*;

/**
 * Vertex Buffer Object stores the data for a list of vertexes.
 *
 * @author OroArmor
 */
public class VertexBufferObject implements Bindable {
    /**
     * The id of the vbo
     */
    private final int vbo_id;

    /**
     * Constructs a {@link VertexBufferObject} with a float array
     *
     * @param data The vertex data
     */
    public VertexBufferObject(final float[] data) {
        vbo_id = glGenBuffers();

        bind();
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);

        Destructor.addDestructable(this::destroy);
    }

    /**
     * Constructs a {@link VertexBufferObject} with a {@link FloatBuffer}
     *
     * @param data
     */
    public VertexBufferObject(final FloatBuffer data) {
        vbo_id = glGenBuffers();

        bind();
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
    }

    @Override
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, vbo_id);
    }

    public void destroy() {
        glDeleteBuffers(vbo_id);
    }

    @Override
    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
}
