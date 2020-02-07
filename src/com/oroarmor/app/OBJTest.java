package com.oroarmor.app;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.oroarmor.core.Destructor;
import com.oroarmor.core.game.Camera;
import com.oroarmor.core.glfw.Display;
import com.oroarmor.core.glfw.GLFWUtil;
import com.oroarmor.core.glfw.GLFWUtil.OpenGLProfile;
import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyHoldEvent;
import com.oroarmor.core.glfw.event.key.KeyPressEvent;
import com.oroarmor.core.glfw.event.key.KeyReleaseEvent;
import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.Renderer;
import com.oroarmor.core.opengl.Shader;
import com.oroarmor.core.opengl.Texture;
import com.oroarmor.util.OBJLoader;
import com.oroarmor.util.ResourceLoader;

public class OBJTest {

	public static void main(String[] args) {

		Matrix4f objectModel = new Matrix4f().translate(0, 0, 100).rotateXYZ((float) -Math.PI / 2, (float) Math.PI, 0)
				.scale(5);

//		Matrix4f camera = new Matrix4f().translate(new Vector3f(0, 0, 0));

		Camera camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));

		// Create a new window with a onKey function that prints the typed key
		Display display = new Display(640, 480, "Open GL Learning") {
			@Override
			public void processKeyHeldEvent(KeyHoldEvent event) {
			}

			@Override
			public void processKeyPressedEvent(KeyPressEvent event) {
			}

			@Override
			public void processKeyReleasedEvent(KeyReleaseEvent event) {
				Key key = event.getKey();
				if (key == this.closeKey) {
					this.close();
				}
			}
		};

		display.enableTransparentcy();

		// Set the OpenGL version to 4.5 core
		GLFWUtil.setWindowHints(4, 5, OpenGLProfile.CORE);

		Mesh cube = OBJLoader.loadOBJ("./res/calicat.obj");

		// Load the shader files
		String shaderName = "weird";

		String vertex = ResourceLoader.loadFile("./res/" + shaderName + "vs.vs");
		String fragment = ResourceLoader.loadFile("./res/" + shaderName + "fs.fs");

		// Create two shaders based on the files
		Shader shader = new Shader(vertex, fragment);

		// Create a renderer
		Renderer renderer = new Renderer();

		// Create a texture and bind it to the square shader

		display.setClearColor(0, 0, 0, 1);

		Texture texture = new Texture("./res/TitansLogo.png");

		texture.bind(0);
		shader.bind();
		shader.setUniform1i("u_Texture", 0);

		shader.setUniform3f("u_lightDir", new Vector3f(0, 1, -1));

		// Dont close the display until its set closed
		while (!display.shouldClose()) {
			camera.tick();
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
		Destructor.destroyAll();

		// Close the display
		display.end();
	}

}
