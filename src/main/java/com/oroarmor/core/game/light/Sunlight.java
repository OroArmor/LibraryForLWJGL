package com.oroarmor.core.game.light;

import org.joml.Vector3f;
import org.joml.Vector4f;

public class Sunlight {
	public Vector4f color = new Vector4f(1, 1, 1, 1);
	public Vector3f direction = new Vector3f(0, 1, 0);

	public Sunlight() {
	}

	public Sunlight(final Vector3f direction, final Vector4f color) {
		this.direction = direction;
		this.color = color;
	}

	public Vector4f getColor() {
		return this.color;
	}

	public Vector3f getDirection() {
		return this.direction;
	}

	public void setColor(final Vector4f color) {
		this.color = color;
	}

	public void setDirection(final Vector3f direction) {
		this.direction = direction;
	}

}
