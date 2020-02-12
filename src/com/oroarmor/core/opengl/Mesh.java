package com.oroarmor.core.opengl;

public class Mesh {

	IndexBufferObject ibo;
	VertexArrayObject vao;

	public Mesh(float[] vertexPositions, int[] indexPositions, VertexBufferLayout layout) {
		long millis = System.currentTimeMillis();

		VertexBufferObject vbo = new VertexBufferObject(vertexPositions);
		vao = new VertexArrayObject();
		vao.addBuffer(vbo, layout);

		ibo = new IndexBufferObject(indexPositions);

		System.out.println(System.currentTimeMillis() - millis);
	}

	public void render(Renderer renderer, Shader shader) {
		renderer.draw(vao, ibo, shader);
	}

}
