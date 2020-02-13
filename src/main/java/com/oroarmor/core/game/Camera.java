package com.oroarmor.core.game;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyStatus;
import com.oroarmor.core.glfw.event.key.hold.KeyHoldEvent;
import com.oroarmor.core.glfw.event.key.press.KeyPressEvent;
import com.oroarmor.core.glfw.event.key.release.KeyReleaseEvent;

public class Camera extends Entity {

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
	Movement upDown = Movement.NONE;
	private float minHeight;

	public Camera(Vector3f position, Vector3f rotation, Vector3f scale) {
		super(position, rotation, scale);
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

		if (key == Key.LEFT_SHIFT) {
			upDown = (upDown == Movement.NONE) ? Movement.DOWN : Movement.NONE;
		} else if (key == Key.SPACE) {
			upDown = (upDown == Movement.NONE) ? Movement.UP : Movement.NONE;
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
		if (key == Key.LEFT_SHIFT) {
			upDown = (upDown == Movement.DOWN) ? Movement.NONE : Movement.UP;
		} else if (key == Key.SPACE) {
			upDown = Movement.NONE;
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

	@Override
	public void update() {

		if(this.positionVector.y < minHeight) {
			this.positionVector.y = minHeight;
			this.velocityVector.y = 0;
		}
		
		float speed = 5f;

		if (KeyStatus.isKeyDown(Key.LEFT_CONTROL)) {
			speed *= 2;
		}

		if (leftRight == Movement.LEFT) {
			positionVector.add(-speed * (float) Math.cos(rotationVector.y), 0,
					-speed * (float) Math.sin(rotationVector.y));
		} else if (leftRight == Movement.RIGHT) {
			positionVector.add(speed * (float) Math.cos(rotationVector.y), 0,
					speed * (float) Math.sin(rotationVector.y));
		}

		if (frontBack == Movement.FOWARD) {
			positionVector.add(speed * (float) Math.cos(Math.PI / 2 + rotationVector.y), 0,
					speed * (float) Math.sin(Math.PI / 2 + rotationVector.y));
		} else if (frontBack == Movement.BACKWARD) {
			positionVector.add(-speed * (float) Math.cos(Math.PI / 2 + rotationVector.y), 0,
					-speed * (float) Math.sin(Math.PI / 2 + rotationVector.y));
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

		if (upDown != Movement.UP) {
			velocityVector.add(0, -speed*0.1f, 0f);
		} else if (upDown == Movement.UP) {
			velocityVector.set(0, speed, 0f);
			upDown = Movement.NONE;
		}
		
		
	}

	public void setMinHeight(float currentHeight) {
		this.minHeight = currentHeight;
	}

	
}
