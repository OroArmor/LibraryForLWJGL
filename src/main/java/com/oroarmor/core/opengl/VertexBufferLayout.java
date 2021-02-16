package com.oroarmor.core.opengl;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * This is a class the explains the layout of a {@link VertexBufferObject}.
 *
 * @author OroArmor
 */
public class VertexBufferLayout {
    /**
     * A list of elements to easily add new {@link VertexBufferElement} to the
     * layout.
     */
    private final ArrayList<VertexBufferElement> vbElements = new ArrayList<>();
    /**
     * The size in bytes of each vertex in the {@link VertexBufferObject}
     */
    private int stride;

    /**
     * Initialize a new {@link VertexBufferLayout}
     */
    public VertexBufferLayout() {
        stride = 0;
    }

    /**
     * @return The stride of the {@link VertexBufferLayout}
     */
    public int getStride() {
        return stride;
    }

    /**
     * @return The {@link VertexBufferElement} array of the
     * {@link VertexBufferLayout}
     */
    public ArrayList<VertexBufferElement> getVbElements() {
        return vbElements;
    }

    /**
     * @param count Add {@code count} floats as a data point in the {@link VertexBufferLayout}
     * @return this
     */
    public VertexBufferLayout pushFloats(int count) {
        vbElements.add(new VertexBufferElement(count, GL_FLOAT, false));
        stride += Float.BYTES * count;
        return this;
    }

    /**
     * @param count Add {@code count} bytes as a data point in the {@link VertexBufferLayout}
     * @return this
     */
    public VertexBufferLayout pushUnsignedBytes(int count) {
        vbElements.add(new VertexBufferElement(count, GL_UNSIGNED_BYTE, false));
        stride += Byte.BYTES * count;
        return this;
    }

    /**
     * @param count Add {@code count} ints as a data point in the
     *              {@link VertexBufferLayout}
     * @return this
     */
    public VertexBufferLayout pushUnsignedInts(int count) {
        vbElements.add(new VertexBufferElement(count, GL_UNSIGNED_INT, false));
        stride += Integer.BYTES * count;
        return this;
    }
}
