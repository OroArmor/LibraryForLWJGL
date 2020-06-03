package com.oroarmor.core.game.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera extends Entity {

	private CameraViewController viewController;

	public Camera(final Vector3f position, final Vector3f rotation) {
		super(position, rotation, new Vector3f(1, 1, 1));
	}

	@Override
	public Matrix4f getModelMatrix() {
		return new Matrix4f().rotate(rotationVector.x, 1, 0, 0).rotate(rotationVector.y, 0, 1, 0)
				.rotate(rotationVector.z, 0, 0, 1).scale(scaleVector).translate(positionVector.negate(new Vector3f()));
	}

	@Override
	public void update(float delta) {
		if (viewController != null) {
			viewController.update(delta);
			this.rotationVector = viewController.rotationVector;
		}
	}

	public CameraViewController getController() {
		return viewController;
	}

	public void setViewController(CameraViewController controller) {
		this.viewController = controller;
	}

}
