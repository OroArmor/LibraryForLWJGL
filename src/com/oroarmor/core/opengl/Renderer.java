package com.oroarmor.core.opengl;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Renderer {

	public void draw(VertexArrayObject vao, IndexBufferObject ibo, Shader shader) {
		shader.bind();
		vao.bind();
		ibo.bind();
		glDrawElements(GL_TRIANGLES, ibo.getCount(), GL_UNSIGNED_INT, NULL);
	}

}
