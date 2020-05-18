package com.oroarmor.core.game.gui.animation;

import org.joml.Vector4f;

import com.oroarmor.core.game.gui.object.box.GUIColorBox;

public class ColorTransition implements IAnimation<GUIColorBox> {

	long duration;
	Vector4f color;

	@Override
	public void animate(GUIColorBox object, float percent) {
		object.setColor(object.getOriginalColor().lerp(color, percent, new Vector4f()));
	}

	public ColorTransition(long duration, Vector4f color) {
		this.duration = duration;
		this.color = color;
	}

	@Override
	public long getDurationInMillis() {
		return duration;
	}

}
