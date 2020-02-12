package com.oroarmor.core.glfw.event.mouse.over;

import com.oroarmor.core.glfw.event.mouse.MouseEvent;
import com.oroarmor.core.glfw.event.mouse.over.enter.MouseEnterEvent;
import com.oroarmor.core.glfw.event.mouse.over.enter.MouseEnterEventListener;
import com.oroarmor.core.glfw.event.mouse.over.leave.MouseLeaveEvent;
import com.oroarmor.core.glfw.event.mouse.over.leave.MouseLeaveEventListener;

import static org.lwjgl.glfw.GLFW.*;

public class MouseOverEvent extends MouseEvent {

	public static void create(long window, int action) {
		if (action == GLFW_TRUE) {
			MouseEnterEventListener.processAllMouseEnterEvent(new MouseEnterEvent(window));
		} else if (action == GLFW_FALSE) {
			MouseLeaveEventListener.processAllMouseLeaveEvent(new MouseLeaveEvent(window));
		}
	}

	MouseOverEventType type;

	public MouseOverEvent(long window, MouseOverEventType type) {
		super(window, MouseEventType.OVER);

		this.type = type;
	}

	public MouseOverEventType getMouseOverEventType() {
		return this.type;
	}

	@Override
	public String toString() {
		return "Over event type: " + type;
	}
}
