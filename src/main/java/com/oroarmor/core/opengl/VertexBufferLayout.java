package com.oroarmor.core.opengl;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;

import java.util.ArrayList;

/**
 * This is a class the explains the layout of a {@link VertexBufferObject}.
 * 
 * @author OroArmor
 *
 */
public class VertexBufferLayout {
	/**
	 * The size in bytes of each vertex in the {@link VertexBufferObject}
	 */
	private int stride;

	/**
	 * A list of elements to easily add new {@link VertexBufferElement} to the
	 * layout.
	 */
	private ArrayList<VertexBufferElement> vbElements = new ArrayList<VertexBufferElement>();

	/**
	 * Initialize a new {@link VertexBufferLayout}
	 */
	public VertexBufferLayout() {
		stride = 0;
	}

	/**
	 * 
	 * @return The stride of the {@link VertexBufferLayout}
	 */
	public int getStride() {
		return stride;
	}

	/**
	 * 
	 * @return The {@link VertexBufferElement} array of the
	 *         {@link VertexBufferLayout}
	 */
	public ArrayList<VertexBufferElement> getVbElements() {
		return vbElements;
	}

	/**
	 * 
	 * @param count Add {@code count} floats as a data point in the
	 *              {@link VertexBufferLayout}
	 */
	public void pushFloats(int count) {
		vbElements.add(new VertexBufferElement(count, GL_FLOAT, false));
		stride += Float.BYTES * count;
	}

	/**
	 * 
	 * @param count Add {@code count} bytes as a data point in the
	 *              {@link VertexBufferLayout}
	 */
	public void pushUnsignedBytes(int count) {
		vbElements.add(new VertexBufferElement(count, GL_UNSIGNED_BYTE, false));
		stride += Byte.BYTES * count;
	}

	/**
	 * 
	 * @param count Add {@code count} ints as a data point in the
	 *              {@link VertexBufferLayout}
	 */
	public void pushUnsignedInts(int count) {
		vbElements.add(new VertexBufferElement(count, GL_UNSIGNED_INT, false));
		stride += Integer.BYTES * count;
	}
}
