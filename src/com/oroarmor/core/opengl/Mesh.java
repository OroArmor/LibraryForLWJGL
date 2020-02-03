package com.oroarmor.core.opengl;

public class Mesh {

	VertexArrayObject vao;
	IndexBufferObject ibo;

	public Mesh(float[] vertexPositions, int[] indexPositions, VertexBufferLayout layout) {
		VertexBufferObject vbo = new VertexBufferObject(vertexPositions);
		vao = new VertexArrayObject();
		vao.addBuffer(vbo, layout);

		ibo = new IndexBufferObject(indexPositions);
	}

	public void render(Renderer renderer, Shader shader) {
		renderer.draw(vao, ibo, shader);
	}

}
