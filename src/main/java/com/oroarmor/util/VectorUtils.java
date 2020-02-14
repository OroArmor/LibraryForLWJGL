package com.oroarmor.util;

import org.joml.Vector3f;

public class VectorUtils {

	/**
	 * Turns a vector into an array
	 * 
	 * @param vector The vector
	 * @return A float array with length of 3, {@code [x,y,z]}
	 */
	public static float[] vectorToArray(Vector3f vector) {
		return new float[] { vector.x, vector.y, vector.z };
	}
}
