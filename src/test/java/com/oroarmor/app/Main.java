package com.oroarmor.app;

import com.oroarmor.core.glfw.Display;
import com.oroarmor.core.glfw.GLFWUtil;
import com.oroarmor.core.glfw.GLFWUtil.OpenGLProfile;
import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.hold.KeyHoldEvent;
import com.oroarmor.core.glfw.event.key.press.KeyPressEvent;
import com.oroarmor.core.glfw.event.key.release.KeyReleaseEvent;
import com.oroarmor.core.glfw.event.mouse.button.press.MousePressEvent;
import com.oroarmor.core.glfw.event.mouse.button.release.MouseReleaseEvent;
import com.oroarmor.core.glfw.event.mouse.over.enter.MouseEnterEvent;
import com.oroarmor.core.glfw.event.mouse.over.leave.MouseLeaveEvent;
import com.oroarmor.core.glfw.event.mouse.position.MousePositionEvent;
import com.oroarmor.core.glfw.event.mouse.scroll.MouseScrollEvent;
import com.oroarmor.core.opengl.*;
import com.oroarmor.util.ResourceLoader;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Main {

	public static void main(final String[] args) {

		// Create a new window with a onKey function that prints the typed key
		final Display display = new Display(640, 640, "Open GL Learning") {

			@Override
			public void processKeyHeldEvent(final KeyHoldEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void processKeyPressedEvent(final KeyPressEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void processKeyReleasedEvent(final KeyReleaseEvent event) {
				if (event.getKey() == Key.ESCAPE) {
					close();
				}
			}

			@Override
			public void processMouseEnterEvent(final MouseEnterEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void processMouseLeaveEvent(final MouseLeaveEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void processMousePositionEvent(final MousePositionEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void processMousePressEvent(final MousePressEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void processMouseReleasedEvent(final MouseReleaseEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void processMouseScrolledEvent(final MouseScrollEvent event) {
				// TODO Auto-generated method stub

			}

		};

		display.enableTransparency();

		// Set the OpenGL version to 4.5 core
		GLFWUtil.setWindowHints(4, 5, OpenGLProfile.CORE);

		// Data for M and a square

		// M
		final float[] letterMVerticies = { -0.5f, -0.5f, -0.5f, 0.6f, -0.3f, -0.5f, -0.3f, 0.1f, 0f, -0.2f, 0f, 0.1f,
				0.3f, 0.1f, 0.3f, -0.5f, 0.5f, 0.6f, 0.5f, -0.5f };

		for (int i = 0; i < letterMVerticies.length; i += 2) {
			letterMVerticies[i] = (letterMVerticies[i] + 1) * display.getWidth() / 2;
			letterMVerticies[i + 1] = (letterMVerticies[i + 1] + 1) * display.getHeight() / 2;
		}

		final int[] letterMIndicies = { 0, 1, 2, //
				1, 2, 3, //
				1, 3, 5, //
				3, 5, 4, //
				9, 8, 7, //
				8, 7, 6, //
				8, 6, 5, //
				6, 5, 4 };

		final float[] squareVerticies = { 0f, 0f, 0f, 1f, //
				100f, 0f, 1f, 1f, //
				100f, 100f, 1f, 0f, //
				0f, 100f, 0f, 0f };

		final int[] squareIndicies = { 0, 1, 2, //
				2, 3, 0 };

		// M "mesh" vao, vbo, and ibo

		final VertexBufferLayout layout = new VertexBufferLayout();
		layout.pushFloats(2);
		final Mesh mMesh = new Mesh(letterMVerticies, letterMIndicies, layout);

		// Square "mesh" vao, vbo, and ibo
		final VertexBufferLayout layout2 = new VertexBufferLayout();
		layout2.pushFloats(2);
		layout2.pushFloats(2);

		final Mesh squareMesh = new Mesh(squareVerticies, squareIndicies, layout2);

		// Load the shader files
		final String vertex = ResourceLoader.loadFileString(Main.class.getClassLoader().getResourceAsStream("com/oroarmor/app/basicvs.vs"));
		final String vertex2 = ResourceLoader.loadFileString(Main.class.getClassLoader().getResourceAsStream("com/oroarmor/app/weirdvs.vs"));
		final String fragment = ResourceLoader.loadFileString(Main.class.getClassLoader().getResourceAsStream("com/oroarmor/app/basicfs.fs"));
		final String frag2 = ResourceLoader.loadFileString(Main.class.getClassLoader().getResourceAsStream("com/oroarmor/app/weirdfs.fs"));

		// Create two shaders based on the files
		final Shader squareShader = new Shader(vertex, fragment);
		final Shader mShader = new Shader(vertex2, frag2);

		// Create a renderer
		final Renderer renderer = new Renderer();

		// Create a texture and bind it to the square shader
		mShader.bind();
		mShader.setUniformMat4f("u_MVP", display.getOrthoViewModel());

//		final BufferedImage texture = new BufferedImage("com/oroarmor/app/test.png");
//
//		System.out.println();
//		System.out.println(texture.getR(255, 255) & 0xFF);
//		System.out.println(texture.getG(255, 255) & 0xFF);
//		System.out.println(texture.getB(255, 255) & 0xFF);
//		System.out.println(texture.getA(255, 255) & 0xFF);

		squareShader.bind();
		squareShader.setUniform1i("u_Texture", 0);
		squareShader.setUniformMat4f("u_MVP", display.getOrthoViewModel());

		display.setClearColor(0, 0, 0, 1);

		final Matrix4f camera = new Matrix4f().translate(new Vector3f(100, 100, 0)).rotateX(-0.01f);

		final Matrix4f squareModel = new Matrix4f().translate(1, 1, 1).scale(1, 1, 1).rotateXYZ(0, 0.1f, 0);
		final Matrix4f mModel = new Matrix4f();

		// Dont close the display until its set closed
		while (!display.shouldClose()) {
			// Clear the display
			display.setClearColor(0.5f,
					(float) Math.sin(0.25f * (System.currentTimeMillis() % (Math.PI * 4000)) / 1000f + 0.56f), 0.8f,
					1f);
			display.clear();
			camera.rotateX(-0.01f);

			final Matrix4f MVP = display.getOrthoViewModel().mul(camera);

			// Bind the mShader and set u_Color
			squareShader.bind();
			squareShader.setUniformMat4f("u_MVP", MVP.mul(squareModel.rotateZ(-0.01f), new Matrix4f()));
			mShader.bind();
			mShader.setUniformMat4f("u_MVP", MVP.mul(mModel.rotateY(0.01f), new Matrix4f()));
			mShader.setUniform4f("u_Color", (float) Math.sin(System.currentTimeMillis() % (Math.PI * 1000d) / 1000f),
					0.2f, 0.8f, 1f);

			// Draw the square and M
			mMesh.render(renderer, mShader);
			squareMesh.render(renderer, squareShader);

			// Render the current frame buffer
			display.render();
		}

		// Destroy all destructables

		// Close the display
		display.close();
	}
}
