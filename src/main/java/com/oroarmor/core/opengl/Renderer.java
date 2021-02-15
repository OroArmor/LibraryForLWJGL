package com.oroarmor.core.opengl;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Class in charge of rendering object to the screen
 *
 * @author OroArmor
 */
public class Renderer {

    /**
     * Renders the {@link VertexArrayObject} with the {@link IndexBufferObject}
     * using the {@link Shader}
     *
     * @param vao    {@link VertexArrayObject}
     * @param ibo    {@link IndexBufferObject}
     * @param shader {@link Shader}
     */
    public void draw(final VertexArrayObject vao, final IndexBufferObject ibo, final Shader shader) {
        shader.bind();
        vao.bind();
        ibo.bind();
        glDrawElements(GL_TRIANGLES, ibo.getCount(), GL_UNSIGNED_INT, NULL);
    }
}
