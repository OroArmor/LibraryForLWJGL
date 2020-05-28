package com.oroarmor.app;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import com.oroarmor.core.game.gui.group.GUIGroup;
import com.oroarmor.core.game.gui.object.box.GUIBox;
import com.oroarmor.core.game.gui.object.box.GUIColorBox;
import com.oroarmor.core.game.gui.shader.GUIShaders;
import com.oroarmor.core.game.gui.shader.font.FontShader;
import com.oroarmor.core.game.gui.text.Font;
import com.oroarmor.core.game.gui.text.FontLoader;
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
import com.oroarmor.core.opengl.Texture;
import com.oroarmor.util.ResourceLoader;

public class GUITest {
	static String textString = "a";

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
				if (event.key != Key.BACKSPACE) {
					char c = '\0';

					if (event.getEventMods().isShift()) {
						c = event.key.getUpperChar();
					} else {
						c = event.key.getLowerChar();
					}

					textString += c == '\0' ? "" : c;

				} else if (textString.length() > 0) {
					textString = textString.substring(0, textString.length() - 1);
				}
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

		// Create a renderer
		final Renderer renderer = new Renderer();

		final Font testFont = FontLoader.loadFontFromData(ResourceLoader.loadFile("./res/bank.fnt"),
				new Texture("./res/bank.png"));

		display.setClearColor(0, 0, 0, 1);

		final FontShader shader = new FontShader();
		shader.bind();
		shader.setTexture(testFont.getTexture());
		shader.setColor(new Vector4f(1, 0, 0, 1));
		shader.setObjectModel(new Matrix4f().translation(0, 0, 0));

		final GUIColorBox box = new GUIColorBox(105, 105, 190, 90, new Vector4f(1, 0, 1, 1));
		final GUIColorBox box2 = new GUIColorBox(105, 205, 190, 90, new Vector4f(0, 0.7f, 0.6f, 1));

		final GUIBox bg = new GUIBox(100, 100, 200, 200);

		final GUIGroup group = new GUIGroup(100, 100) {
		};

		group.addChildren(box, box2);

		final GUIGroup ui = new GUIGroup(0, 0) {
		};

		ui.addChildren(group, bg);

		while (!display.shouldClose()) {
			// Clear the display
			display.clear();

			GUIShaders.updateShaderView(display.getOrthoViewModel());

			shader.setColor(new Vector4f(1, 0, (float) Math.sin(System.currentTimeMillis() / 1000d) * 0.5f + 0.5f, 1));

			shader.setOrthoView(display.getOrthoViewModel());
			testFont.getTextMesh(textString, 1f, display.getWidth()).render(renderer, shader);

			ui.renderChildren(renderer);
			display.render();
		}

		// Close the display
		display.close();
	}
}
