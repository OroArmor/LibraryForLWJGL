package com.oroarmor.core.glfw.event.mouse.over.enter;

import com.oroarmor.core.glfw.event.mouse.over.MouseOverEvent;
import com.oroarmor.core.glfw.event.mouse.over.MouseOverEventType;

public class MouseEnterEvent extends MouseOverEvent {

	public MouseEnterEvent(long window) {
		super(window, MouseOverEventType.ENTER);
	}

}
