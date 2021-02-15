package com.oroarmor.core.opengl;

import static org.lwjgl.opengl.GL11.*;

/**
 * This class represents one element of a Vertex when passed into OpenGL
 *
 * @author OroArmor
 */
public class VertexBufferElement {
    /**
     * The count of bytes
     */
    private final int count;
    /**
     * If the data is normalized
     */
    private final boolean normalized;
    /**
     * The type of the {@link VertexBufferElement}. One of the OpenGL data types.
     */
    private final int type;

    /**
     * @param count      The count in bytes of the data
     * @param type       The type of the data
     * @param normalized If the data is normalized
     */
    public VertexBufferElement(final int count, final int type, final boolean normalized) {
        this.count = count;
        this.type = type;
        this.normalized = normalized;
    }

    /**
     * @param type The type of the {@link VertexBufferElement}
     * @return The size in bytes of the type
     */
    public static int getSize(final int type) {
        switch (type) {
            case GL_FLOAT:
                return 4;
            case GL_UNSIGNED_INT:
                return 4;
            case GL_UNSIGNED_BYTE:
                return 1;
            default:
                return 0;
        }
    }

    /**
     * @return The count in bytes
     */
    public int getCount() {
        return count;
    }

    /**
     * @return The type of the data
     */
    public int getType() {
        return type;
    }

    /**
     * @return True the data is normalize
     */
    public boolean isNormalized() {
        return normalized;
    }
}