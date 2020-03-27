package com.oroarmor.core.game.gui.object.box;

import org.joml.Vector4f;

public class GUIColorBox extends GUIBox {

	public GUIColorBox(float x, float y, float width, float height, Vector4f color) {
		super(x, y, width, height);
		this.color = color;
	}

}
