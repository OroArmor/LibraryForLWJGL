package com.oroarmor.app;

import com.oroarmor.core.Destructor;
import com.oroarmor.core.game.entity.Camera;
import com.oroarmor.core.game.entity.CameraViewController;
import com.oroarmor.core.game.light.Sunlight;
import com.oroarmor.core.game.terrain.TerrainMesh;
import com.oroarmor.core.game.terrain.TerrainShader;
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
import com.oroarmor.core.opengl.Renderer;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class TerrainTest {
	public static boolean sunToggle = false;

	public static void main(String[] args) {

		Sunlight sun = new Sunlight(new Vector3f(-1, -1, 0), new Vector4f(1f, .9f, .8f, 1));

		CameraViewController controller = new CameraViewController(0.01f);
		Camera camera = new Camera(new Vector3f(0, 100, 0), new Vector3f(0, -(float) Math.PI / 2, 0));
		camera.setViewController(controller);
		// Create a new window with a onKey function that prints the typed key
		Display display = new Display(640, 480, "Open GL Learning - Terrain") {
			@Override
			public void processKeyHeldEvent(KeyHoldEvent event) {
			}

			@Override
			public void processKeyPressedEvent(KeyPressEvent event) {
				if (event.getKey() == Key.G) {
					sun.setColor(sunToggle ? new Vector4f(1, 1, 1, 1) : new Vector4f(1f, .9f, .8f, 1));
					sunToggle = !sunToggle;
				}
			}

			@Override
			public void processKeyReleasedEvent(KeyReleaseEvent event) {
				Key key = event.getKey();
				if (key == closeKey) {
					close();
				} else if (key == Key.F11) {
					fullscreen();
				}
			}

			@Override
			public void processMouseEnterEvent(MouseEnterEvent event) {
			}

			@Override
			public void processMouseLeaveEvent(MouseLeaveEvent event) {
			}

			@Override
			public void processMousePositionEvent(MousePositionEvent event) {
			}

			@Override
			public void processMousePressEvent(MousePressEvent event) {
			}

			@Override
			public void processMouseReleasedEvent(MouseReleaseEvent event) {
			}

			@Override
			public void processMouseScrolledEvent(MouseScrollEvent event) {
			}
		};

		display.enableTransparency();

		display.setCullFace(CullFace.BACK);

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
		while (display.shouldNotClose()) {

			camera.tick(1 / 60f);
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
				if (terrains[i].getMesh() != null) {
					terrains[i].getMesh().render(renderer, shader);
				}

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
