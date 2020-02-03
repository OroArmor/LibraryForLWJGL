package com.oroarmor.app;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.oroarmor.core.Destructor;
import com.oroarmor.core.glfw.Display;
import com.oroarmor.core.glfw.GLFWUtil;
import com.oroarmor.core.glfw.GLFWUtil.OpenGLProfile;
import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyEvent.KeyEventType;
import com.oroarmor.core.glfw.event.key.KeyHoldEvent;
import com.oroarmor.core.glfw.event.key.KeyPressEvent;
import com.oroarmor.core.glfw.event.key.KeyReleaseEvent;
import com.oroarmor.core.glfw.event.key.KeyStatus;
import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.Renderer;
import com.oroarmor.core.opengl.Shader;
import com.oroarmor.core.opengl.Texture;
import com.oroarmor.util.OBJLoader;
import com.oroarmor.util.ResourceLoader;

public class OBJTest {

	public static void main(String[] args) {

		Matrix4f objectModel = new Matrix4f().translate(0, 0, 100).scale(5).rotateX((float) Math.PI / 2);

		Matrix4f camera = new Matrix4f().translate(new Vector3f(0, 0, 0));

		// Create a new window with a onKey function that prints the typed key
		Display display = new Display(640, 480, "Open GL Learning") {
			@Override
			public void processKeyPressedEvent(KeyPressEvent event) {
				Key key = event.getKey();
				KeyEventType action = event.getKeyEventType();

				System.out.println(key + " " + action);
			}

			@Override
			public void processKeyHeldEvent(KeyHoldEvent event) {
				Key key = event.getKey();
				KeyEventType action = event.getKeyEventType();

				System.out.println(key + " " + action);
			}

			@Override
			public void processKeyReleasedEvent(KeyReleaseEvent event) {
				Key key = event.getKey();
				KeyEventType action = event.getKeyEventType();

				System.out.println(key + " " + action);

				if (key == this.closeKey) {
					this.close();
				}
			}
		};

		display.enableTransparentcy();

		// Set the OpenGL version to 4.5 core
		GLFWUtil.setWindowHints(4, 5, OpenGLProfile.CORE);

		Mesh cube = OBJLoader.loadOBJ("./res/TitansLogo.obj");

		// Load the shader files
		String shaderName = "basic";

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

		shader.setUniform3f("u_lightDir", new Vector3f(-1, -1, -1));

		shader.setUniform3f("u_lightPos", new Vector3f(0, 100, 0));
		shader.setUniform3f("u_lightColor", new Vector3f(1, 0.2f, 0));
		shader.setUniform1f("u_lightStrength", 100f);

		// Dont close the display until its set closed
		while (!display.shouldClose()) {

			if (KeyStatus.isKeyDown(Key.A)) {
				camera.translateLocal(5f, 0, 0);
			} else if (KeyStatus.isKeyDown(Key.D)) {
				camera.translateLocal(-5f, 0, 0);
			}

			if (KeyStatus.isKeyDown(Key.W)) {
				camera.translateLocal(0, 0f, -5f);
			} else if (KeyStatus.isKeyDown(Key.S)) {
				camera.translateLocal(0, 0, 5f);
			}

			if (KeyStatus.isKeyDown(Key.RIGHT)) {
				camera.rotateLocalY(-0.1f);
			} else if (KeyStatus.isKeyDown(Key.LEFT)) {
				camera.rotateLocalY(0.1f);
			}

			if (KeyStatus.isKeyDown(Key.DOWN)) {
				camera.rotateLocalX(-0.1f);
			} else if (KeyStatus.isKeyDown(Key.UP)) {
				camera.rotateLocalX(0.1f);
			}

			// Clear the display

			display.clear();

			Matrix4f MV = display.getPerspectiveViewModel(90).mul(camera);

			// Bind the mShader and set u_Color
			shader.bind();
			shader.setUniformMat4f("u_MV", MV);
			shader.setUniformMat4f("u_P", objectModel);

			shader.setUniform3f("u_lightDir", new Vector3f(-1, -1, 1));

			shader.setUniform3f("u_lightPos", new Vector3f(1, -0.5f, -1));
			shader.setUniform3f("u_lightColor", new Vector3f(1, 0.2f, 1f));
			shader.setUniform1f("u_lightStrength", 100f);

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
