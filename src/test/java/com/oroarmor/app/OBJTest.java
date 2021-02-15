package com.oroarmor.app;

import com.oroarmor.core.game.entity.Camera;
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
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class OBJTest {

	public static void main(final String[] args) {

		final Matrix4f objectModel = new Matrix4f().translate(0, 0, 100)
				.rotateXYZ((float) -Math.PI / 2, (float) Math.PI, 0).scale(1);

//		Matrix4f camera = new Matrix4f().translate(new Vector3f(0, 0, 0));

		final Camera camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));

		// Create a new window with a onKey function that prints the typed key
		final Display display = new Display(640, 480, "Open GL Learning") {

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

			}

			@Override
			public void processMouseScrolledEvent(final MouseScrollEvent event) {
				// TODO Auto-generated method stub

			}
		};

		display.enableTransparency();
		display.setCullFace(CullFace.FRONT);

		// Set the OpenGL version to 4.5 core
		GLFWUtil.setWindowHints(4, 5, OpenGLProfile.CORE);

		final Mesh cube = OBJLoader.loadOBJ(ResourceLoader.loadFileString(OBJTest.class.getClassLoader().getResourceAsStream("com/oroarmor/app/TitansLogo.obj")));

		// Load the shader files
		final String shaderName = "basic";

		final String vertex = ResourceLoader.loadFileString(OBJTest.class.getClassLoader().getResourceAsStream("com/oroarmor/app/" + shaderName + "vs.vs"));
		final String fragment = ResourceLoader.loadFileString(OBJTest.class.getClassLoader().getResourceAsStream("com/oroarmor/app/" + shaderName + "fs.fs"));

		// Create two shaders based on the files
		final Shader shader = new Shader(vertex, fragment);

		shader.compile();
		// Create a renderer
		final Renderer renderer = new Renderer();

		// Create a texture and bind it to the square shader

		display.setClearColor(0, 0, 0, 1);

		final Texture texture = new Texture("com/oroarmor/app/TitansLogo.png");

		texture.bind(1);
		shader.bind();
		shader.setUniform1i("u_Texture", 1);

		shader.setUniform3f("u_lightDir", new Vector3f(0, 1, -1));

		// Dont close the display until its set closed
		while (!display.shouldClose()) {
			camera.tick(8f);
			// Clear the display
			display.clear();

			final Matrix4f MV = display.getPerspectiveViewModel(90).mul(camera.getModelMatrix());

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
