package com.oroarmor.core.glfw.event.mouse.button;

import com.oroarmor.core.glfw.event.GLFWEventMods;
import com.oroarmor.core.glfw.event.mouse.MouseEvent;
import com.oroarmor.core.glfw.event.mouse.MouseStatus;
import com.oroarmor.core.glfw.event.mouse.button.press.MouseButtonPressEventListener;
import com.oroarmor.core.glfw.event.mouse.button.press.MousePressEvent;
import com.oroarmor.core.glfw.event.mouse.button.release.MouseButtonReleaseEventListener;
import com.oroarmor.core.glfw.event.mouse.button.release.MouseReleaseEvent;

public class MouseButtonEvent extends MouseEvent {
	public static void create(final long window, final int button, final int action, final GLFWEventMods mods) {
		if (action == MouseButtonEventType.PRESS.getAction()) {
			final MousePressEvent event = new MousePressEvent(MouseButton.getButtonFromCode(button),
					MouseStatus.getMouseX(), MouseStatus.getMouseY(), window, mods);
			MouseButtonPressEventListener.processAllMousePressEvent(event);
		}
		if (action == MouseButtonEventType.RELEASE.getAction()) {
			final MouseReleaseEvent event = new MouseReleaseEvent(MouseButton.getButtonFromCode(button),
					MouseStatus.getMouseX(), MouseStatus.getMouseY(), window, mods);
			MouseButtonReleaseEventListener.processAllMouseReleaseEvent(event);
		}
	}

	private final MouseButton button;
	private final MouseButtonEventType type;

	private final float x, y;

	public MouseButtonEvent(final MouseButton button, final float x, final float y, final MouseButtonEventType type,
			final long window, final GLFWEventMods mods) {
		super(window, MouseEventType.BUTTON, mods);
		this.button = button;
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public MouseButton getButton() {
		return button;
	}

	public MouseButtonEventType getMouseButtonEventType() {
		return type;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	@Override
	public String toString() {
		return "button id: " + button.getMouseButtonID() + ", button: " + button + ", type: "
				+ getMouseButtonEventType();
	}

}
