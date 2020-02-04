package com.oroarmor.core.game;

import org.joml.Vector3f;

import com.oroarmor.core.glfw.event.Event;
import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyHoldEvent;
import com.oroarmor.core.glfw.event.key.KeyPressEvent;
import com.oroarmor.core.glfw.event.key.KeyReleaseEvent;

public class Camera extends Entity {

	private static enum Movement {
		LEFT, RIGHT, NONE, FOWARD, BACKWARD, UP, DOWN;
	}

	private static enum Look {
		LEFT, RIGHT, UP, DOWN, NONE, ROLL_LEFT, ROLL_RIGHT;
	}

	Movement frontBack = Movement.NONE;
	Movement leftRight = Movement.NONE;
	Movement upDown = Movement.NONE;
	Look lookPitch = Look.NONE;
	Look lookYaw = Look.NONE;
	Look lookRoll = Look.NONE;

	public Camera(Vector3f position, Vector3f rotation, Vector3f scale) {
		super(position, rotation, scale);
	}

	@Override
	public void processKeyPressedEvent(KeyPressEvent event) {
		System.out.println(event);

		Key key = event.getKey();
		// Movement
		if (key == Key.A) {
			System.out.println("A!");
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
	}

	@Override
	public void processKeyHeldEvent(KeyHoldEvent event) {

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
			upDown = (upDown == Movement.UP) ? Movement.NONE : Movement.DOWN;
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
	public void processEvent(Event event) {

	}

	@Override
	public void update() {
		if (leftRight == Movement.LEFT) {
			positionVector.add(5f, 0, 0);
		} else if (leftRight == Movement.RIGHT) {
			positionVector.add(-5f, 0, 0);
		}

		if (frontBack == Movement.FOWARD) {
			positionVector.add(0, 0f, -5f);
		} else if (frontBack == Movement.BACKWARD) {
			positionVector.add(0, 0, 5f);
		}

		if (lookYaw == Look.RIGHT) {
			rotationVector.add(0, -0.1f, 0);
		} else if (lookYaw == Look.LEFT) {
			rotationVector.add(0, 0.1f, 0);
		}

		if (lookPitch == Look.DOWN) {
			rotationVector.add(-0.1f, 0, 0);
		} else if (lookPitch == Look.UP) {
			rotationVector.add(0.1f, 0, 0);
		}

		if (upDown == Movement.DOWN) {
			positionVector.add(0, 5f, 0f);
		} else if (upDown == Movement.UP) {
			positionVector.add(0, -5f, 0f);
		}
	}
}
