package com.oroarmor.core.opengl;

import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.util.ArrayList;

import com.oroarmor.core.Bindable;
import com.oroarmor.core.Destructable;
import com.oroarmor.core.Destructor;

/**
 * This is a class for storing a {@link VertexBufferObject} and a
 * {@link VertexBufferLayout} object into one OpenGL concept, reducing the
 * number of needed calls
 *
 * @author OroArmor
 *
 */
public class VertexArrayObject implements Bindable, Destructable {

	/**
	 * Id of the {@link VertexArrayObject}
	 */
	private final int vao_id;

	/**
	 * Creates a new {@link VertexArrayObject}
	 */
	public VertexArrayObject() {
		this.vao_id = glGenVertexArrays();

		Destructor.addDestructable(this);
	}

	/**
	 * Adds a {@link VertexBufferObject} and a {@link VertexBufferLayout} to the
	 * {@link VertexArrayObject}
	 *
	 * @param vbo      The {@link VertexBufferObject} of the
	 *                 {@link VertexArrayObject}
	 * @param vbLayout The {@link VertexBufferLayout} of the
	 *                 {@link VertexArrayObject}
	 */
	public void addBuffer(final VertexBufferObject vbo, final VertexBufferLayout vbLayout) {
		this.bind();
		vbo.bind();

		final ArrayList<VertexBufferElement> elements = vbLayout.getVbElements();

		int currentOffset = 0;

		for (int i = 0; i < elements.size(); i++) {
			final VertexBufferElement element = elements.get(i);
			glEnableVertexAttribArray(i);
			glVertexAttribPointer(i, element.getCount(), element.getType(), element.isNormalized(),
					vbLayout.getStride(), currentOffset);

			currentOffset += element.getCount() * VertexBufferElement.getSize(element.getType());
		}
	}

	@Override
	public void bind() {
		glBindVertexArray(this.vao_id);
	}

	@Override
	public void destroy() {
		glDeleteVertexArrays(this.vao_id);
	}

	@Override
	public void unbind() {
		glBindVertexArray(0);
	}

}
