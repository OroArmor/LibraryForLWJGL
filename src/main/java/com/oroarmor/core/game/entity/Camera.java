package com.oroarmor.core.game.entity;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import com.oroarmor.core.game.entity.physics.PhysicsEntity;
import com.oroarmor.core.glfw.event.key.Key;
import com.oroarmor.core.glfw.event.key.KeyEventListener;
import com.oroarmor.core.glfw.event.key.KeyStatus;
import com.oroarmor.core.glfw.event.key.hold.KeyHoldEvent;
import com.oroarmor.core.glfw.event.key.press.KeyPressEvent;
import com.oroarmor.core.glfw.event.key.release.KeyReleaseEvent;

public class Camera extends PhysicsEntity implements KeyEventListener {

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

	public Camera(final Vector3f position, final Vector3f rotation) {
		super(position, rotation, new Vector3f(1, 1, 1), 1);
		this.addToKeyListeners();
	}

	@Override
	public Matrix4f getModelMatrix() {
		return new Matrix4f().rotate(this.rotationVector.x, 1, 0, 0).rotate(this.rotationVector.y, 0, 1, 0)
				.rotate(this.rotationVector.z, 0, 0, 1).scale(this.scaleVector).translate(this.positionVector.negate(new Vector3f()));
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

		// Movement
		if (key == Key.A) {
			this.leftRight = this.leftRight == Movement.NONE ? Movement.LEFT : Movement.NONE;
		} else if (key == Key.D) {
			this.leftRight = this.leftRight == Movement.NONE ? Movement.RIGHT : Movement.NONE;
		}

		if (key == Key.W) {
			this.frontBack = this.frontBack == Movement.NONE ? Movement.FOWARD : Movement.NONE;
		} else if (key == Key.S) {
			this.frontBack = this.frontBack == Movement.NONE ? Movement.BACKWARD : Movement.NONE;
		}

		if (key == Key.SPACE) {
//			this.addForce(new Vector3f(0, 130, 0));
		}

		if (key == Key.LEFT_SHIFT) {
//			this.addForce(new Vector3f(0, -130, 0));
		}

		// Look TODO: change to mouse
		if (key == Key.UP) {
			this.lookPitch = this.lookPitch == Look.NONE ? Look.UP : Look.NONE;
		} else if (key == Key.DOWN) {
			this.lookPitch = this.lookPitch == Look.NONE ? Look.DOWN : Look.NONE;
		}
		if (key == Key.LEFT) {
			this.lookYaw = this.lookYaw == Look.NONE ? Look.LEFT : Look.NONE;
		} else if (key == Key.RIGHT) {
			this.lookYaw = this.lookYaw == Look.NONE ? Look.RIGHT : Look.NONE;
		}

		if (key == Key.PERIOD) {
			this.positionVector = new Vector3f(0, 0, 0);
			this.rotationVector = new Vector3f(0, 0, 0);
			this.velocityVector = new Vector3f(0, 0, 0);
		}
	}

	@Override
	public void processKeyReleasedEvent(final KeyReleaseEvent event) {
		final Key key = event.getKey();

		// Movement
		if (key == Key.A) {
			this.leftRight = this.leftRight == Movement.LEFT ? Movement.NONE : Movement.RIGHT;
		} else if (key == Key.D) {
			this.leftRight = this.leftRight == Movement.RIGHT ? Movement.NONE : Movement.LEFT;
		}
		if (key == Key.W) {
			this.frontBack = this.frontBack == Movement.FOWARD ? Movement.NONE : Movement.BACKWARD;
		} else if (key == Key.S) {
			this.frontBack = this.frontBack == Movement.BACKWARD ? Movement.NONE : Movement.FOWARD;
		}

		// Look TODO: change to mouse
		if (key == Key.UP) {
			this.lookPitch = this.lookPitch == Look.UP ? Look.NONE : Look.DOWN;
		} else if (key == Key.DOWN) {
			this.lookPitch = this.lookPitch == Look.DOWN ? Look.NONE : Look.UP;
		}
		if (key == Key.LEFT) {
			this.lookYaw = this.lookYaw == Look.LEFT ? Look.NONE : Look.RIGHT;
		} else if (key == Key.RIGHT) {
			this.lookYaw = this.lookYaw == Look.RIGHT ? Look.NONE : Look.LEFT;
		}
	}

	@Override
	public void setActive(final boolean active) {
	}

	@Override
	public void update(final float delta) {

		float speed = 0.005f;

		if (KeyStatus.isKeyDown(Key.LEFT_CONTROL)) {
			speed *= 2;
		}

		if (this.leftRight == Movement.LEFT) {
			this.accelerateLocalXZ(new Vector2f(-speed, 0));
		} else if (this.leftRight == Movement.RIGHT) {
			this.accelerateLocalXZ(new Vector2f(speed, 0));
		}

		if (this.frontBack == Movement.FOWARD) {
			this.accelerateLocalXZ(new Vector2f(0, speed));
		} else if (this.frontBack == Movement.BACKWARD) {
			this.accelerateLocalXZ(new Vector2f(0, -speed));
		}

		this.drag(0.3f, 0, 0.3f);

		if (this.lookYaw == Look.RIGHT) {
			this.rotationVector.add(0, -0.001f, 0);
		} else if (this.lookYaw == Look.LEFT) {
			this.rotationVector.add(0, 0.001f, 0);
		}

		if (this.lookPitch == Look.DOWN && this.rotationVector.x > (float) -Math.PI / 2) {
			this.rotationVector.add(-0.001f, 0, 0);
		} else if (this.lookPitch == Look.UP && this.rotationVector.x < (float) Math.PI / 2) {
			this.rotationVector.add(0.001f, 0, 0);
		}

		if (KeyStatus.isKeyDown(Key.SPACE)) {
			this.velocityVector.add(new Vector3f(0, speed / 5, 0));
		} else if (KeyStatus.isKeyDown(Key.LEFT_SHIFT)) {
			this.velocityVector.add(new Vector3f(0, -speed / 5, 0));
		} else {
			this.velocityVector.y = 0;
		}

	}

}
