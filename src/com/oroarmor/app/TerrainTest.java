package com.oroarmor.app;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import com.oroarmor.core.Destructor;
import com.oroarmor.core.game.Camera;
import com.oroarmor.core.game.light.Pointlight;
import com.oroarmor.core.game.light.Sunlight;
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
	public static boolean sunToggle = false;

	public static void main(String[] args) {

		Sunlight sun = new Sunlight(new Vector3f(-1, -1, 0), new Vector4f(1f, .9f, .8f, 1));

		Camera camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
		// Create a new window with a onKey function that prints the typed key
		Display display = new Display(640, 480, "Open GL Learning") {
			@Override
			public void processKeyHeldEvent(KeyHoldEvent event) {
			}

			@Override
			public void processKeyPressedEvent(KeyPressEvent event) {
				if (event.getKey() == Key.G) {
					sun.setColor((sunToggle) ? new Vector4f(1, 1, 1, 1) : new Vector4f(1f, .9f, .8f, 1));
					sunToggle = !sunToggle;
				}
			}

			@Override
			public void processKeyReleasedEvent(KeyReleaseEvent event) {
				Key key = event.getKey();
				if (key == this.closeKey) {
					this.close();
				} else if (key == Key.F11) {
					this.fullscreen();
				}
			}
		};

		display.enableTransparentcy();

		// Set the OpenGL version to 4.5 core
		GLFWUtil.setWindowHints(4, 5, OpenGLProfile.CORE);

		int size = 200;

		TerrainMesh terrain = new TerrainMesh(size, size, 0, 0);
		TerrainMesh terrain2 = new TerrainMesh(size, size, size - 1, 0);
		TerrainMesh terrain3 = new TerrainMesh(size, size, size - 1, size - 1);
		TerrainMesh terrain4 = new TerrainMesh(size, size, 0, size - 1);

		Matrix4f objectModel = new Matrix4f().scale(10, 5, 10).translate(0, -30, 0).translate(-size, 0, size);
		Matrix4f object2 = new Matrix4f().scale(10, 5, 10).translate(size - 1, -30, 0).translate(-size, 0, size);
		Matrix4f object3 = new Matrix4f().scale(10, 5, 10).translate(size - 1, -30, -(size - 1)).translate(-size, 0,
				size);
		Matrix4f object4 = new Matrix4f().scale(10, 5, 10).translate(0, -30, -(size - 1)).translate(-size, 0, size);

		// Load the shader files
		TerrainShader shader = new TerrainShader();
		shader.update();

		shader.addSunlight(sun);
		shader.addPointlight(new Pointlight(new Vector4f(1, 0.1f, 0.1f, 1), new Vector3f(0, 100, 0), 500));
		shader.addPointlight(new Pointlight(new Vector4f(0.1f, 0.1f, 1, 1), new Vector3f(-size * 5, 100, 0), 1000));
		shader.addPointlight(
				new Pointlight(new Vector4f(0.1f, 1, 0.1f, 1), new Vector3f(size * 5, 100, size * 5), 1000));

		// Create a renderer
		Renderer renderer = new Renderer();

		// Create a texture and bind it to the square shader

		display.setClearColor(0.5f, 0.7f, 0.9f, 1);

		// Dont close the display until its set closed
		while (!display.shouldClose()) {
			camera.tick();
			// Clear the display
			display.clear();

			Matrix4f MV = display.getPerspectiveViewModel(70).mul(camera.getModelMatrix());

			// Bind the mShader and set u_Color
			shader.bind();
			shader.update();
			shader.setUniformMat4f("u_MV", MV);
			shader.setUniformMat4f("u_P", objectModel);
			terrain.getMesh().render(renderer, shader);

			shader.bind();
			shader.setUniformMat4f("u_P", object2);
			terrain2.getMesh().render(renderer, shader);

			shader.bind();
			shader.setUniformMat4f("u_P", object3);
			terrain3.getMesh().render(renderer, shader);

			shader.bind();
			shader.setUniformMat4f("u_P", object4);
			terrain4.getMesh().render(renderer, shader);
			// Render the current frame buffer
			display.render();
		}

		// Destroy all destructables
		Destructor.destroyAll();

		// Close the display
		display.end();
	}

}
