package com.oroarmor.app;

import org.joml.Vector4f;

import com.oroarmor.core.game.gui.GUICallback;
import com.oroarmor.core.game.gui.group.GUIGroup;
import com.oroarmor.core.game.gui.object.box.GUIBox;
import com.oroarmor.core.game.gui.object.box.GUIColorBox;
import com.oroarmor.core.game.gui.object.box.TexturedGUIBox;
import com.oroarmor.core.game.gui.shader.GUIShaders;
import com.oroarmor.core.glfw.Display;
import com.oroarmor.core.glfw.GLFWUtil;
import com.oroarmor.core.glfw.GLFWUtil.OpenGLProfile;
import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.hold.KeyHoldEvent;
import com.oroarmor.core.glfw.event.key.press.KeyPressEvent;
import com.oroarmor.core.glfw.event.key.release.KeyReleaseEvent;
import com.oroarmor.core.glfw.event.mouse.button.MouseButton;
import com.oroarmor.core.glfw.event.mouse.button.press.MousePressEvent;
import com.oroarmor.core.glfw.event.mouse.button.release.MouseReleaseEvent;
import com.oroarmor.core.glfw.event.mouse.over.enter.MouseEnterEvent;
import com.oroarmor.core.glfw.event.mouse.over.leave.MouseLeaveEvent;
import com.oroarmor.core.glfw.event.mouse.position.MousePositionEvent;
import com.oroarmor.core.glfw.event.mouse.scroll.MouseScrollEvent;
import com.oroarmor.core.opengl.Renderer;
import com.oroarmor.core.opengl.Texture;

public class GUIExampleTest {

	static GUIGroup main, sub1, sub2, sub3;

	public static void main(String[] args) {

		// Create a new window with a onKey function that prints the typed key
		Display display = new Display(640, 640, "Open GL Learning") {

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
				// TODO Auto-generated method stub
			}

			@Override
			public void processMouseScrolledEvent(MouseScrollEvent event) {
				// TODO Auto-generated method stub

			}

		};

		display.enableTransparency();

		// Set the OpenGL version to 4.5 core
		GLFWUtil.setWindowHints(4, 5, OpenGLProfile.CORE);

		sub3 = new GUIGroup(0, 0) {
		};

		TexturedGUIBox mario = new TexturedGUIBox(300, 300, 100, 100, new Texture("./res/mario-nes.png"));

		sub3.addChildren(mario);

		// Create a renderer
		Renderer renderer = new Renderer();
		display.setClearColor(0.8f, 0.8f, 0.8f, 1);

		GUIColorBox sub1box1 = new GUIColorBox(205, 105, 190, 90, new Vector4f(1, 0, 1, 1));
		sub1box1.setActive(false);

		sub1box1.setCallback(new GUICallback() {
			@Override
			public void onRelease(MouseButton button, boolean inBounds) {
				if (!inBounds)
					return;
				sub3.makeVisable(sub3.isVisable());
			}
		});

		GUIColorBox sub1box2 = new GUIColorBox(205, 205, 190, 90, new Vector4f(0, 0.7f, 0.6f, 1));
		sub1box2.setActive(false);

		sub1box2.setCallback(new GUICallback() {
			@Override
			public void onRelease(MouseButton button, boolean inBounds) {
				if (!inBounds)
					return;
				sub1.makeVisable(false);
				main.makeVisable(true);

			}
		});

		GUIBox sub1bg = new GUIBox(200, 100, 200, 200);
		sub1bg.setActive(false);
		sub1 = new GUIGroup(100, 100, true) {
		};

		sub1.addChildren(sub1box1, sub1box2, sub1bg, sub3);

		GUIColorBox sub2box1 = new GUIColorBox(105, 205, 190, 90, new Vector4f(1, 1, 0, 1));
		GUIColorBox sub2box2 = new GUIColorBox(105, 305, 190, 90, new Vector4f(0, 0.3f, 0, 1));

		sub2box2.setCallback(new GUICallback() {
			@Override
			public void onRelease(MouseButton button, boolean inBounds) {
				if (!inBounds)
					return;
				sub2.makeVisable(false);
				main.makeVisable(true);

			}
		});

		GUIBox sub2bg = new GUIBox(100, 200, 200, 200);

		sub2box1.setActive(false);
		sub2box2.setActive(false);
		sub2bg.setActive(false);

		sub2 = new GUIGroup(100, 100, true) {
		};

		sub2.addChildren(sub2box1, sub2box2, sub2bg);

		GUIGroup ui = new GUIGroup(0, 0) {
		};

		GUIColorBox mainbox1 = new GUIColorBox(105, 105, 190, 90, new Vector4f(1, 0, 0, 1));

		mainbox1.setActive(true);

		mainbox1.setCallback(new GUICallback() {
			@Override
			public void onRelease(MouseButton button, boolean inBounds) {
				System.out.println(inBounds);
				if (!inBounds)
					return;
				System.out.println("main1");
				sub1.makeVisable(true);
				main.makeVisable(false);
			}

		});

		GUIColorBox mainbox2 = new GUIColorBox(105, 205, 190, 90, new Vector4f(0, 0, 0.6f, 1));
		mainbox2.setActive(true);
		mainbox2.setCallback(new GUICallback() {
			@Override
			public void onRelease(MouseButton button, boolean inBounds) {

				if (!inBounds)
					return;

				sub2.makeVisable(true);
				main.makeVisable(false);

			}
		});

		GUIBox mainbg = new GUIBox(100, 100, 200, 200);

		main = new GUIGroup(100, 100) {
		};

		main.addChildren(mainbox1, mainbox2, mainbg);
		ui.addChildren(main, sub1, sub2);

		while (!display.shouldClose()) {
			// Clear the display
			display.clear();

			GUIShaders.updateShaderView(display.getOrthoViewModel());
			ui.renderChildren(renderer);

			display.render();
		}

		// Close the display
		display.close();
	}
}
