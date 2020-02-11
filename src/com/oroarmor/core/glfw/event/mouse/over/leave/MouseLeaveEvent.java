package com.oroarmor.core.glfw.event.mouse.over.leave;

import com.oroarmor.core.glfw.event.mouse.over.MouseOverEvent;
import com.oroarmor.core.glfw.event.mouse.over.MouseOverEventType;

public class MouseLeaveEvent extends MouseOverEvent {

	public MouseLeaveEvent(long window) {
		super(window, MouseOverEventType.LEAVE);
	}

}
