package com.oroarmor.app;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.oroarmor.core.Destructor;
import com.oroarmor.core.game.Camera;
import com.oroarmor.core.game.terrain.TerrainMesh;
import com.oroarmor.core.game.terrain.TerrainShader;
import com.oroarmor.core.glfw.Display;
import com.oroarmor.core.glfw.GLFWUtil;
import com.oroarmor.core.glfw.GLFWUtil.OpenGLProfile;
import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyHoldEvent;
import com.oroarmor.core.glfw.event.key.KeyPressEvent;
import com.oroarmor.core.glfw.event.key.KeyReleaseEvent;
import com.oroarmor.core.opengl.Renderer;

public class TerrainTest {

	public static void main(String[] args) {

		Camera camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
		// Create a new window with a onKey function that prints the typed key
		Display display = new Display(640, 480, "Open GL Learning") {
			@Override
			public void processKeyPressedEvent(KeyPressEvent event) {
			}

			@Override
			public void processKeyHeldEvent(KeyHoldEvent event) {
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

		TerrainMesh terrain = new TerrainMesh(400, 400, 0, 0);
		Matrix4f objectModel = new Matrix4f().scale(10).translate(-200, 0, -200);

		// Load the shader files
		TerrainShader shader = new TerrainShader();
		shader.update();

		// Create a renderer
		Renderer renderer = new Renderer();

		// Create a texture and bind it to the square shader

		display.setClearColor(0, 0, 0, 1);

		// Dont close the display until its set closed
		while (!display.shouldClose()) {
			camera.tick();
			// Clear the display
			display.clear();

			Matrix4f MV = display.getPerspectiveViewModel(70).mul(camera.getModelMatrix());

			// Bind the mShader and set u_Color
			shader.bind();
			shader.setUniformMat4f("u_MV", MV);
			shader.setUniformMat4f("u_P", objectModel);
			shader.setUniform3f("u_lightDir", new Vector3f(-1, -1, 0));

			terrain.getMesh().render(renderer, shader);
			// Render the current frame buffer
			display.render();
		}

		// Destroy all destructables
		Destructor.destroyAll();

		// Close the display
		display.end();
	}

}
