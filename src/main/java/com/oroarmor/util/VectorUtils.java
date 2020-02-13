package com.oroarmor.util;

import org.joml.Vector3f;

public class VectorUtils {

	public static float[] vectorToArray(Vector3f vector) {
		return new float[] { vector.x, vector.y, vector.z };
	}
}
