package com.oroarmor.core.opengl;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.FloatBuffer;

import com.oroarmor.core.Bindable;
import com.oroarmor.core.Destructable;
import com.oroarmor.core.Destructor;

/**
 * Vertex Buffer Object stores the data for a list of vertexes.
 *
 * @author OroArmor
 *
 */
public class VertexBufferObject implements Bindable, Destructable {
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
		this.vbo_id = glGenBuffers();

		this.bind();
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);

		Destructor.addDestructable(this);
	}

	/**
	 * Constructs a {@link VertexBufferObject} with a {@link FloatBuffer}
	 *
	 * @param vertexPositions
	 */
	public VertexBufferObject(final FloatBuffer data) {
		this.vbo_id = glGenBuffers();

		this.bind();
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
	}

	@Override
	public void bind() {
		glBindBuffer(GL_ARRAY_BUFFER, this.vbo_id);
	}

	@Override
	public void destroy() {
		glDeleteBuffers(this.vbo_id);
	}

	@Override
	public void unbind() {
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
}
