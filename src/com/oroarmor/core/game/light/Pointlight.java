package com.oroarmor.core.game.light;

import org.joml.Vector3f;
import org.joml.Vector4f;

public class Pointlight {
	public Vector4f color = new Vector4f(0, 0, 0, 0);
	public Vector3f position = new Vector3f(0, 0, 0);
	public float strength = 0;

	public Pointlight(Vector4f color, Vector3f position, float strength) {
		this.color = color;
		this.position = position;
		this.strength = strength;
	}

	public Pointlight() {
	}

	public Vector4f getColor() {
		return color;
	}

	public void setColor(Vector4f color) {
		this.color = color;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getStrength() {
		return strength;
	}

	public void setStrength(float strength) {
		this.strength = strength;
	}

}
