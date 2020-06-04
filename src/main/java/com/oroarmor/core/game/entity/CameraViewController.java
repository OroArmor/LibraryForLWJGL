package com.oroarmor.core.game.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.oroarmor.core.game.entity.physics.PhysicsEntity;
import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyEventListener;
import com.oroarmor.core.glfw.event.key.hold.KeyHoldEvent;
import com.oroarmor.core.glfw.event.key.press.KeyPressEvent;
import com.oroarmor.core.glfw.event.key.release.KeyReleaseEvent;

public class CameraViewController extends PhysicsEntity implements KeyEventListener {

	private static enum Look {
		DOWN, LEFT, NONE, RIGHT, ROLL_LEFT, ROLL_RIGHT, UP;
	}

	Look lookPitch = Look.NONE;
	Look lookRoll = Look.NONE;
	Look lookYaw = Look.NONE;
	private final float rotationSpeed;

	public CameraViewController(float rotationSpeed) {
		super(new Vector3f(), new Vector3f(), new Vector3f(1, 1, 1), 1);
		addToKeyListeners();
		this.rotationSpeed = rotationSpeed;
	}

	@Override
	public Matrix4f getModelMatrix() {
		return new Matrix4f().rotate(rotationVector.x, 1, 0, 0).rotate(rotationVector.y, 0, 1, 0)
				.rotate(rotationVector.z, 0, 0, 1).scale(scaleVector).translate(positionVector.negate(new Vector3f()));
	}

	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public void processKeyHeldEvent(final KeyHoldEvent event) {

	}

	@Override
	public void processKeyPressedEvent(final KeyPressEvent event) {
		final Key key = event.getKey();

		// Look TODO: change to mouse
		if (key == Key.UP) {
			lookPitch = lookPitch == Look.NONE ? Look.UP : Look.NONE;
		} else if (key == Key.DOWN) {
			lookPitch = lookPitch == Look.NONE ? Look.DOWN : Look.NONE;
		}
		if (key == Key.LEFT) {
			lookYaw = lookYaw == Look.NONE ? Look.LEFT : Look.NONE;
		} else if (key == Key.RIGHT) {
			lookYaw = lookYaw == Look.NONE ? Look.RIGHT : Look.NONE;
		}

		if (key == Key.PERIOD) {
			positionVector = new Vector3f(0, 0, 0);
			rotationVector = new Vector3f(0, 0, 0);
			velocityVector = new Vector3f(0, 0, 0);
		}
	}

	@Override
	public void processKeyReleasedEvent(final KeyReleaseEvent event) {
		final Key key = event.getKey();

		// Look TODO: change to mouse
		if (key == Key.UP) {
			lookPitch = lookPitch == Look.UP ? Look.NONE : Look.DOWN;
		} else if (key == Key.DOWN) {
			lookPitch = lookPitch == Look.DOWN ? Look.NONE : Look.UP;
		}
		if (key == Key.LEFT) {
			lookYaw = lookYaw == Look.LEFT ? Look.NONE : Look.RIGHT;
		} else if (key == Key.RIGHT) {
			lookYaw = lookYaw == Look.RIGHT ? Look.NONE : Look.LEFT;
		}
	}

	@Override
	public void setActive(final boolean active) {
	}

	@Override
	public void update(final float delta) {

		if (lookYaw == Look.RIGHT) {
			rotationVector.add(0, -rotationSpeed, 0);
		} else if (lookYaw == Look.LEFT) {
			rotationVector.add(0, rotationSpeed, 0);
		}

		if (lookPitch == Look.DOWN && rotationVector.x > (float) -Math.PI / 2) {
			rotationVector.add(-rotationSpeed, 0, 0);
		} else if (lookPitch == Look.UP && rotationVector.x < (float) Math.PI / 2) {
			rotationVector.add(rotationSpeed, 0, 0);
		}

	}

}
