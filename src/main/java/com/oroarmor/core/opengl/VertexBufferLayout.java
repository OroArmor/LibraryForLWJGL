package com.oroarmor.core.opengl;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;

import java.util.ArrayList;

public class VertexBufferLayout {
	private int stride;

	private ArrayList<VertexBufferElement> vbElements = new ArrayList<VertexBufferElement>();

	public VertexBufferLayout() {
		stride = 0;
	}

	public int getStride() {
		return stride;
	}

	public ArrayList<VertexBufferElement> getVbElements() {
		return vbElements;
	}

	public void pushFloats(int count) {
		vbElements.add(new VertexBufferElement(count, GL_FLOAT, false));
		stride += Float.BYTES * count;
	}

	public void pushUnsignedBytes(int count) {
		vbElements.add(new VertexBufferElement(count, GL_UNSIGNED_BYTE, false));
		stride += Byte.BYTES * count;
	}

	public void pushUnsignedInts(int count) {
		vbElements.add(new VertexBufferElement(count, GL_UNSIGNED_INT, false));
		stride += Integer.BYTES * count;
	}
}
