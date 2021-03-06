package com.oroarmor.app;

import com.oroarmor.core.game.gui.GUICallback;
import com.oroarmor.core.game.gui.animation.ColorTransition;
import com.oroarmor.core.game.gui.animation.Easing;
import com.oroarmor.core.game.gui.animation.ScaleAnimation;
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
import com.oroarmor.core.openal.AudioMaster;
import com.oroarmor.core.openal.AudioSource;
import com.oroarmor.core.opengl.Renderer;
import com.oroarmor.core.opengl.Texture;
import org.joml.Vector4f;

public class GUIExampleTest {

	static GUIGroup main, sub1, sub2, sub3;
	static float soundVolume = 0.5f;

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
					close();
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

		TexturedGUIBox mario = new TexturedGUIBox(300, 300, 100, 100, new Texture("com/oroarmor/app/mario-nes.png"));

		sub3.addChildren(mario);

		// Create a renderer
		Renderer renderer = new Renderer();
		display.setClearColor(0.8f, 0.8f, 0.8f, 1);

		GUIColorBox sub1box1 = new GUIColorBox(205, 105, 190, 90, new Vector4f(1, 0, 1, 1));
		sub1box1.setActive(false);

		sub1box1.setCallback(new GUICallback() {
			@Override
			public void onHover() {
				sub1box1.setCurrentColorAsOriginal();
				sub1box1.triggerAnimation(new ColorTransition(200L, Easing.EASE_IN_OUT_SIN, new Vector4f(1, 0.2f, 1, 1)));
				sub1box1.triggerAnimation(new ScaleAnimation<>(200L, Easing.EASE_IN_OUT_SIN, 0.025f));
			}

			@Override
			public void onHoverStop() {
				sub1box1.setCurrentColorAsOriginal();
				sub1box1.triggerAnimation(new ColorTransition(200L, Easing.EASE_IN_OUT_SIN, new Vector4f(1, 0, 1, 1)));
				sub1box1.triggerAnimation(new ScaleAnimation<>(200L, Easing.EASE_IN_OUT_SIN, 0f));
			}

			@Override
			public void onRelease(MouseButton button, boolean inBounds) {
				if (!inBounds) {
					return;
				}
				sub3.makeVisible(sub3.isVisible());
			}
		});

		GUIColorBox sub1box2 = new GUIColorBox(205, 205, 190, 90, new Vector4f(0, 0.7f, 0.6f, 1));
		sub1box2.setActive(false);

		sub1box2.setCallback(new GUICallback() {
			@Override
			public void onHover() {
				sub1box2.setCurrentColorAsOriginal();
				sub1box2.triggerAnimation(
						new ColorTransition(200L, Easing.EASE_IN_OUT_SIN, new Vector4f(0.2f, 0.9f, 0.8f, 1)));
				sub1box2.triggerAnimation(new ScaleAnimation<>(200L, Easing.EASE_IN_OUT_SIN, 0.025f));
			}

			@Override
			public void onHoverStop() {
				sub1box2.setCurrentColorAsOriginal();
				sub1box2.triggerAnimation(
						new ColorTransition(200L, Easing.EASE_IN_OUT_SIN, new Vector4f(0, 0.7f, 0.6f, 1)));
				sub1box2.triggerAnimation(new ScaleAnimation<>(200L, Easing.EASE_IN_OUT_SIN, 0f));
			}

			@Override
			public void onRelease(MouseButton button, boolean inBounds) {
				if (!inBounds) {
					return;
				}
				sub1.makeVisible(false);
				main.makeVisible(true);

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
			public void onHover() {
				sub2box2.setCurrentColorAsOriginal();
				sub2box2.triggerAnimation(
						new ColorTransition(200L, Easing.EASE_IN_OUT_SIN, new Vector4f(0.2f, 0.5f, 0.2f, 1)));
				sub2box2.triggerAnimation(new ScaleAnimation<>(200L, Easing.EASE_IN_OUT_SIN, 0.025f));
			}

			@Override
			public void onHoverStop() {
				sub2box2.setCurrentColorAsOriginal();
				sub2box2.triggerAnimation(new ColorTransition(200L, Easing.EASE_IN_OUT_SIN, new Vector4f(0, 0.3f, 0, 1)));
				sub2box2.triggerAnimation(new ScaleAnimation<>(200L, Easing.EASE_IN_OUT_SIN, 0f));
			}

			@Override
			public void onRelease(MouseButton button, boolean inBounds) {
				if (!inBounds) {
					return;
				}
				sub2.makeVisible(false);
				main.makeVisible(true);

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
			public void onHover() {
				mainbox1.setCurrentColorAsOriginal();
				mainbox1.triggerAnimation(
						new ColorTransition(200L, Easing.EASE_IN_OUT_SIN, new Vector4f(1, 0.2f, 0.2f, 1)));
				mainbox1.triggerAnimation(new ScaleAnimation<>(200L, Easing.EASE_IN_OUT_SIN, 0.025f));
			}

			@Override
			public void onHoverStop() {
				mainbox1.setCurrentColorAsOriginal();
				mainbox1.triggerAnimation(new ColorTransition(200L, Easing.EASE_IN_OUT_SIN, new Vector4f(1, 0, 0, 1)));
				mainbox1.triggerAnimation(new ScaleAnimation<>(200L, Easing.EASE_IN_OUT_SIN, 0f));
			}

			@Override
			public void onRelease(MouseButton button, boolean inBounds) {
				if (!inBounds) {
					return;
				}
				sub1.makeVisible(true);
				sub3.makeVisible(false);
				main.makeVisible(false);

			}

		});

		GUIColorBox mainbox2 = new GUIColorBox(105, 205, 190, 90, new Vector4f(0, 0, 0.6f, 1));
		mainbox2.setActive(true);
		mainbox2.setCallback(new GUICallback() {
			@Override
			public void onHover() {
				mainbox2.setCurrentColorAsOriginal();
				mainbox2.triggerAnimation(new ColorTransition(100L, new Vector4f(0.2f, 0.2f, 1, 1)));
				mainbox2.triggerAnimation(new ScaleAnimation<>(200L, 0.025f));
			}

			@Override
			public void onHoverStop() {
				mainbox2.setCurrentColorAsOriginal();
				mainbox2.triggerAnimation(new ColorTransition(100L, new Vector4f(0f, 0f, 1, 1)));
				mainbox2.triggerAnimation(new ScaleAnimation<>(200L, 0f));
			}

			@Override
			public void onRelease(MouseButton button, boolean inBounds) {

				if (!inBounds) {
					return;
				}

				sub2.makeVisible(true);
				main.makeVisible(false);

			}
		});

		GUIBox mainbg = new GUIBox(100, 100, 200, 200);

		main = new GUIGroup(100, 100) {
		};

		main.addChildren(mainbox1, mainbox2, mainbg);

		TexturedGUIBox volUp = new TexturedGUIBox(400, 100, 40, 40, new Texture("com/oroarmor/app/plus.png"));
		volUp.setCallback(new GUICallback() {
			@Override
			public void onRelease(MouseButton button, boolean inBounds) {
				if (!inBounds) {
					return;
				}

				soundVolume += 0.1f;

				if (soundVolume > 1) {
					soundVolume = 1;
				}
			}
		});

		TexturedGUIBox volDown = new TexturedGUIBox(400, 160, 40, 40, new Texture("com/oroarmor/app/minus.png"));
		volDown.setCallback(new GUICallback() {
			@Override
			public void onRelease(MouseButton button, boolean inBounds) {
				if (!inBounds) {
					return;
				}

				soundVolume -= 0.1f;

				if (soundVolume < 0) {
					soundVolume = 0;
				}
			}
		});

		GUIGroup vol = new GUIGroup(400, 100) {
		};

		vol.addChildren(volUp, volDown);

		ui.addChildren(main, sub1, sub2, vol);

		AudioMaster.initialize();
		AudioMaster.loadSound("com/oroarmor/app/space_racer.ogg", "space_racer");
		AudioSource radio = new AudioSource();

		radio.playSound(AudioMaster.getSound("space_racer"));

		while (display.shouldNotClose()) {
			// Clear the display
			display.clear();

			GUIShaders.updateShaderView(display.getOrthoViewModel());
			ui.renderChildren(renderer);

			display.render();

			radio.setGain(soundVolume);
			if (radio.isFinished()) {
				radio.playSound(AudioMaster.getSound("space_racer"));
			}
		}

		// Close the display
		display.close();
	}
}
