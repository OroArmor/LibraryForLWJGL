package com.oroarmor.core.opengl;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Renderer {
	public void draw(VertexArrayObject vao, IndexBufferObject ibo, Shader shader) {
		shader.bind();
		vao.bind();
		ibo.bind();
		glDrawElements(GL_TRIANGLES, ibo.getCount(), GL_UNSIGNED_INT, NULL);
	}
}
