package com.oroarmor.core.game;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyStatus;
import com.oroarmor.core.glfw.event.key.hold.KeyHoldEvent;
import com.oroarmor.core.glfw.event.key.press.KeyPressEvent;
import com.oroarmor.core.glfw.event.key.release.KeyReleaseEvent;

public class Camera extends PhysicsEntity {

	private static enum Look {
		DOWN, LEFT, NONE, RIGHT, ROLL_LEFT, ROLL_RIGHT, UP;
	}

	private static enum Movement {
		BACKWARD, DOWN, FOWARD, LEFT, NONE, RIGHT, UP;
	}

	Movement frontBack = Movement.NONE;
	Movement leftRight = Movement.NONE;
	Look lookPitch = Look.NONE;
	Look lookRoll = Look.NONE;
	Look lookYaw = Look.NONE;
	private float minHeight;
	Movement upDown = Movement.NONE;

	public Camera(Vector3f position, Vector3f rotation, Vector3f scale) {
		super(position, rotation, scale, 1);
	}

	@Override
	public Matrix4f getModelMatrix() {
		return new Matrix4f().rotate(rotationVector.x, 1, 0, 0).rotate(rotationVector.y, 0, 1, 0)
				.rotate(rotationVector.z, 0, 0, 1).scale(scaleVector).translate(positionVector.negate(new Vector3f()));
	}

	@Override
	public void processKeyHeldEvent(KeyHoldEvent event) {

	}

	@Override
	public void processKeyPressedEvent(KeyPressEvent event) {
		Key key = event.getKey();

		// Movement
		if (key == Key.A) {
			leftRight = (leftRight == Movement.NONE) ? Movement.LEFT : Movement.NONE;
		} else if (key == Key.D) {
			leftRight = (leftRight == Movement.NONE) ? Movement.RIGHT : Movement.NONE;
		}

		if (key == Key.W) {
			frontBack = (frontBack == Movement.NONE) ? Movement.FOWARD : Movement.NONE;
		} else if (key == Key.S) {
			frontBack = (frontBack == Movement.NONE) ? Movement.BACKWARD : Movement.NONE;
		}

		if (key == Key.SPACE) {
			this.addForce(new Vector3f(0, 130, 0));
		}

		// Look TODO: change to mouse
		if (key == Key.UP) {
			lookPitch = (lookPitch == Look.NONE) ? Look.UP : Look.NONE;
		} else if (key == Key.DOWN) {
			lookPitch = (lookPitch == Look.NONE) ? Look.DOWN : Look.NONE;
		}
		if (key == Key.LEFT) {
			lookYaw = (lookYaw == Look.NONE) ? Look.LEFT : Look.NONE;
		} else if (key == Key.RIGHT) {
			lookYaw = (lookYaw == Look.NONE) ? Look.RIGHT : Look.NONE;
		}

		if (key == Key.PERIOD) {
			positionVector = new Vector3f(0, 0, 0);
			rotationVector = new Vector3f(0, 0, 0);
		}
	}

	@Override
	public void processKeyReleasedEvent(KeyReleaseEvent event) {
		Key key = event.getKey();

		// Movement
		if (key == Key.A) {
			leftRight = (leftRight == Movement.LEFT) ? Movement.NONE : Movement.RIGHT;
		} else if (key == Key.D) {
			leftRight = (leftRight == Movement.RIGHT) ? Movement.NONE : Movement.LEFT;
		}
		if (key == Key.W) {
			frontBack = (frontBack == Movement.FOWARD) ? Movement.NONE : Movement.BACKWARD;
		} else if (key == Key.S) {
			frontBack = (frontBack == Movement.BACKWARD) ? Movement.NONE : Movement.FOWARD;
		}

		// Look TODO: change to mouse
		if (key == Key.UP) {
			lookPitch = (lookPitch == Look.UP) ? Look.NONE : Look.DOWN;
		} else if (key == Key.DOWN) {
			lookPitch = (lookPitch == Look.DOWN) ? Look.NONE : Look.UP;
		}
		if (key == Key.LEFT) {
			lookYaw = (lookYaw == Look.LEFT) ? Look.NONE : Look.RIGHT;
		} else if (key == Key.RIGHT) {
			lookYaw = (lookYaw == Look.RIGHT) ? Look.NONE : Look.LEFT;
		}
	}

	public void setMinHeight(float currentHeight) {
		this.minHeight = currentHeight;
	}

	@Override
	public void update(float delta) {

		this.addAcceleration(new Vector3f(0, -9.81f, 0));
		if (this.positionVector.y <= minHeight && this.accelerationVector.y < 0) {
			this.positionVector.y = minHeight;
			this.velocityVector.y = 0;
			this.accelerationVector.y = 0;
		}

		float speed = 5f;

		if (KeyStatus.isKeyDown(Key.LEFT_CONTROL)) {
			speed *= 2;
		}

		if (leftRight == Movement.LEFT) {
			this.accelerateLocalXZ(new Vector2f(-speed, 0));
		} else if (leftRight == Movement.RIGHT) {
			this.accelerateLocalXZ(new Vector2f(speed, 0));
		}

		if (frontBack == Movement.FOWARD) {
			this.accelerateLocalXZ(new Vector2f(0, speed));
		} else if (frontBack == Movement.BACKWARD) {
			this.accelerateLocalXZ(new Vector2f(0, -speed));
		}

		if (frontBack == Movement.NONE && leftRight == Movement.NONE) {
			this.velocityVector.mul(0.9f, 1, 0.9f);
		}

		if (lookYaw == Look.RIGHT) {
			rotationVector.add(0, -0.1f, 0);
		} else if (lookYaw == Look.LEFT) {
			rotationVector.add(0, 0.1f, 0);
		}

		if (lookPitch == Look.DOWN && rotationVector.x > (float) -Math.PI / 2) {
			rotationVector.add(-0.1f, 0, 0);
		} else if (lookPitch == Look.UP && rotationVector.x < (float) Math.PI / 2) {
			rotationVector.add(0.1f, 0, 0);
		}

	}

}
