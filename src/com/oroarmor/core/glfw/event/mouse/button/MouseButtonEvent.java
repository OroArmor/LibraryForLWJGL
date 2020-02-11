package com.oroarmor.core.glfw.event.mouse.button;

import com.oroarmor.core.glfw.event.mouse.MouseEvent;
import com.oroarmor.core.glfw.event.mouse.MouseStatus;
import com.oroarmor.core.glfw.event.mouse.button.press.MouseButtonPressEventListener;
import com.oroarmor.core.glfw.event.mouse.button.press.MousePressEvent;
import com.oroarmor.core.glfw.event.mouse.button.release.MouseButtonReleaseEventListener;
import com.oroarmor.core.glfw.event.mouse.button.release.MouseReleaseEvent;

public class MouseButtonEvent extends MouseEvent {
	private MouseButton button;
	private MouseButtonEventType type;
	private float x, y;

	public MouseButtonEvent(MouseButton button, float x, float y, MouseButtonEventType type, long window) {
		super(window, MouseEventType.BUTTON);
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

	public static void create(long window, int button, int action) {
		if (action == MouseButtonEventType.PRESS.getAction()) {
			MousePressEvent event = new MousePressEvent(MouseButton.getButtonFromCode(button), MouseStatus.getMouseX(),
					MouseStatus.getMouseY(), window);
			MouseButtonPressEventListener.processAllMousePressEvent(event);
		}
		if (action == MouseButtonEventType.RELEASE.getAction()) {
			MouseReleaseEvent event = new MouseReleaseEvent(MouseButton.getButtonFromCode(button),
					MouseStatus.getMouseX(), MouseStatus.getMouseY(), window);
			MouseButtonReleaseEventListener.processAllMouseReleaseEvent(event);
		}
	}

	@Override
	public String toString() {
		return "button id: " + button.getMouseButtonID() + ", button: " + button + ", type: "
				+ getMouseButtonEventType();
	}

}
