package com.oroarmor.core.opengl;

import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.IntBuffer;

import com.oroarmor.core.Bindable;
import com.oroarmor.core.Destructable;
import com.oroarmor.core.Destructor;

/**
 * A class that represents an Index Buffer for OpenGL
 *
 * @author OroArmor
 *
 */
public class IndexBufferObject implements Bindable, Destructable {
	/**
	 * The count of indexes
	 */
	private final int count;

	/**
	 * The OpenGL id of the indes
	 */
	private final int ibo_id;

	/**
	 * Creates a new {@link IndexBufferObject} with an integer array
	 *
	 * @param data The data for the {@link IndexBufferObject}
	 */
	public IndexBufferObject(final int[] data) {
		this.ibo_id = glGenBuffers();

		this.bind();
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, GL_STATIC_DRAW);

		this.count = data.length;

		Destructor.addDestructable(this);
	}

	/**
	 * Creates a new {@link IndexBufferObject} with an {@link IntBuffer}
	 *
	 * @param data The data for the {@link IndexBufferObject}
	 */
	public IndexBufferObject(final IntBuffer data) {
		this.ibo_id = glGenBuffers();

		this.bind();
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, GL_STATIC_DRAW);

		this.count = data.limit();
	}

	@Override
	public void bind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.ibo_id);
	}

	@Override
	public void destroy() {
		glDeleteBuffers(this.ibo_id);
	}

	/**
	 *
	 * @return The count of indices in the {@link IndexBufferObject}
	 */
	public int getCount() {
		return this.count;
	}

	@Override
	public void unbind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
}
