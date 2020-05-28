package com.oroarmor.core.game.entity.physics;

import org.joml.Vector3f;

@Deprecated
public class PhysicComponent {

	private Vector3f position;
	private Vector3f velocity;
	private Vector3f acceleration;

	public PhysicComponent(final Vector3f position, final Vector3f velocity, final Vector3f acceleration) {
		this.position = position;
		this.velocity = velocity;
		this.acceleration = acceleration;
	}

	public void addAcceleration(final Vector3f add) {
		acceleration.add(add);
	}

	public Vector3f getAcceleration() {
		return acceleration;
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getVelocity() {
		return velocity;
	}

	public void setAcceleration(final Vector3f acceleration) {
		this.acceleration = acceleration;
	}

	public void setPosition(final Vector3f position) {
		this.position = position;
	}

	public void setVelocity(final Vector3f velocity) {
		this.velocity = velocity;
	}

	public void update(final float delta) {
		velocity.add(acceleration.mul(delta, new Vector3f()));
		position.add(velocity);
		acceleration.zero();
	}

}
