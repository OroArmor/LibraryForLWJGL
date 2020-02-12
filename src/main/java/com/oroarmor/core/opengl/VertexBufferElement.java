package com.oroarmor.core.opengl;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;

public class VertexBufferElement {
	public static int getSize(int type) {
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

	private int count;
	private boolean normalized;

	private int type;

	public VertexBufferElement(int count, int type, boolean normalized) {
		this.count = count;
		this.type = type;
		this.normalized = normalized;
	}

	public int getCount() {
		return count;
	}

	public int getType() {
		return type;
	}

	public boolean isNormalized() {
		return normalized;
	}
}