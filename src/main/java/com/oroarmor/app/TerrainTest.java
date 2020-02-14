package com.oroarmor.app;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import com.oroarmor.core.Destructor;
import com.oroarmor.core.game.Camera;
import com.oroarmor.core.game.light.Sunlight;
import com.oroarmor.core.game.terrain.TerrainMesh;
import com.oroarmor.core.game.terrain.TerrainShader;
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
import com.oroarmor.core.opengl.Renderer;

public class TerrainTest {
	public static boolean sunToggle = false;

	public static void main(String[] args) {

		Sunlight sun = new Sunlight(new Vector3f(-1, -1, 0), new Vector4f(1f, .9f, .8f, 1));

		Camera camera = new Camera(new Vector3f(0, 100, 0), new Vector3f(0, -(float) Math.PI / 2, 0),
				new Vector3f(1, 1, 1));
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

			@Override
			public void processMouseEnterEvent(MouseEnterEvent event) {
				System.out.println(event);
			}

			@Override
			public void processMouseLeaveEvent(MouseLeaveEvent event) {
				System.out.println(event);
			}

			@Override
			public void processMousePositionEvent(MousePositionEvent event) {
				System.out.println(event);
			}

			@Override
			public void processMousePressEvent(MousePressEvent event) {
				System.out.println(event);

			}

			@Override
			public void processMouseReleasedEvent(MouseReleaseEvent event) {
				System.out.println(event);

			}

			@Override
			public void processMouseScrolledEvent(MouseScrollEvent event) {
				System.out.println(event);
			}
		};

		display.enableTransparentcy();

		// Set the OpenGL version to 4.5 core
		GLFWUtil.setWindowHints(4, 5, OpenGLProfile.CORE);

		int size = 200;

		int count = 5;

		TerrainMesh[] terrains = new TerrainMesh[count * count];
		Matrix4f[] terrainModels = new Matrix4f[count * count];

		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				terrains[i * count + j] = new TerrainMesh(size, size, (size - 1) * i, -(size - 1) * j);
				terrainModels[i * count + j] = new Matrix4f().translate((size - 1) * i, -30, (size - 1) * j);
			}
		}

		// Load the shader files
		TerrainShader shader = new TerrainShader();
		shader.update();

		shader.addSunlight(sun);

		// Create a renderer
		Renderer renderer = new Renderer();

		// Create a texture and bind it to the square shader

		display.setClearColor(0.5f, 0.7f, 0.9f, 1);

		// Dont close the display until its set closed

		while (!display.shouldClose()) {

			Vector3f cameraPos = camera.getPosition();

			int x = (int) (cameraPos.x / size);
			int y = (int) (cameraPos.z / size);

			try {
				float currentHeight = terrains[x * count + y].getHeightMap()[(int) cameraPos.x
						- size * x][(int) (cameraPos.z) - size * y];

				camera.setMinHeight(Math.max(currentHeight * TerrainMesh.maxHeight - TerrainMesh.maxHeight * 0.25f, 0));
			} catch (Exception e) {
			}

			camera.tick();
			// Clear the display
			display.clear();

			Matrix4f MV = display.getPerspectiveViewModel(70).mul(camera.getModelMatrix());

			// Bind the mShader and set u_Color
			shader.bind();
			shader.update();
			shader.setUniformMat4f("u_MV", MV);

			for (int i = 0; i < terrainModels.length; i++) {
				shader.bind();
				shader.setUniformMat4f("u_P", terrainModels[i]);
				if (terrains[i].getMesh() != null)
					terrains[i].getMesh().render(renderer, shader);

			}
			// Render the current frame buffer
			display.render();
		}

		// Destroy all destructables
		Destructor.destroyAll();

		// Close the display
		display.end();
	}

}
