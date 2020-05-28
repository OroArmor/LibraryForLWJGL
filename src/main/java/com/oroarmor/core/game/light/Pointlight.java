package com.oroarmor.core.game.light;

import org.joml.Vector3f;
import org.joml.Vector4f;

public class Pointlight {
	public Vector4f color = new Vector4f(0, 0, 0, 0);
	public Vector3f position = new Vector3f(0, 0, 0);
	public float strength = 0;

	public Pointlight() {
	}

	public Pointlight(final Vector4f color, final Vector3f position, final float strength) {
		this.color = color;
		this.position = position;
		this.strength = strength;
	}

	public Vector4f getColor() {
		return color;
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getStrength() {
		return strength;
	}

	public void setColor(final Vector4f color) {
		this.color = color;
	}

	public void setPosition(final Vector3f position) {
		this.position = position;
	}

	public void setStrength(final float strength) {
		this.strength = strength;
	}

}
