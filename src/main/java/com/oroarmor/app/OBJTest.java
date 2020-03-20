package com.oroarmor.app;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.oroarmor.core.game.Camera;
import com.oroarmor.core.glfw.Display;
import com.oroarmor.core.glfw.Display.CullFace;
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
import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.Renderer;
import com.oroarmor.core.opengl.Shader;
import com.oroarmor.core.opengl.Texture;
import com.oroarmor.util.OBJLoader;
import com.oroarmor.util.ResourceLoader;

public class OBJTest {

	public static void main(String[] args) {

		Matrix4f objectModel = new Matrix4f().translate(0, 0, 100).rotateXYZ((float) -Math.PI / 2, (float) Math.PI, 0)
				.scale(10);

//		Matrix4f camera = new Matrix4f().translate(new Vector3f(0, 0, 0));

		Camera camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));

		// Create a new window with a onKey function that prints the typed key
		Display display = new Display(640, 480, "Open GL Learning") {

			@Override
			public void processKeyHeldEvent(KeyHoldEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void processKeyPressedEvent(KeyPressEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void processKeyReleasedEvent(KeyReleaseEvent event) {
				if (event.getKey() == Key.ESCAPE) {
					this.close();
				}

			}

			@Override
			public void processMouseEnterEvent(MouseEnterEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void processMouseLeaveEvent(MouseLeaveEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void processMousePositionEvent(MousePositionEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void processMousePressEvent(MousePressEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void processMouseReleasedEvent(MouseReleaseEvent event) {

			}

			@Override
			public void processMouseScrolledEvent(MouseScrollEvent event) {
				// TODO Auto-generated method stub

			}
		};

		display.enableTransparency();
		display.setCullFace(CullFace.FRONT);

		// Set the OpenGL version to 4.5 core
		GLFWUtil.setWindowHints(4, 5, OpenGLProfile.CORE);

		Mesh cube = OBJLoader.loadOBJ("./LibraryForLWJGL/res/TitansLogo.obj");

		// Load the shader files
		String shaderName = "basic";

		String vertex = ResourceLoader.loadFile("./LibraryForLWJGL/res/" + shaderName + "vs.vs");
		String fragment = ResourceLoader.loadFile("./LibraryForLWJGL/res/" + shaderName + "fs.fs");

		// Create two shaders based on the files
		Shader shader = new Shader(vertex, fragment);

		shader.compile();
		// Create a renderer
		Renderer renderer = new Renderer();

		// Create a texture and bind it to the square shader

		display.setClearColor(0, 0, 0, 1);

		Texture texture = new Texture("./LibraryForLWJGL/res/TitansLogo.png");

		texture.bind(1);
		shader.bind();
		shader.setUniform1i("u_Texture", 1);

		shader.setUniform3f("u_lightDir", new Vector3f(0, 1, -1));

		// Dont close the display until its set closed
		while (!display.shouldClose()) {
			camera.tick(0.05f);
			// Clear the display
			display.clear();

			Matrix4f MV = display.getPerspectiveViewModel(90).mul(camera.getModelMatrix());

			// Bind the mShader and set u_Color
			shader.bind();
			shader.setUniformMat4f("u_MV", MV);
			shader.setUniformMat4f("u_P", objectModel);

			cube.render(renderer, shader);

			// Render the current frame buffer
			display.render();
		}

		// Destroy all destructables

		// Close the display
		display.end();
	}

}
