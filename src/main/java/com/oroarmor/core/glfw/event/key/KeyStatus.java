package com.oroarmor.core.glfw.event.key;

public class KeyStatus {
	private static boolean[] down = new boolean[348];

	public static boolean isKeyDown(Key key) {
		if (key == Key.NOT_FOUND) {
			return false;
		}

		return down[key.getKeyCode()];
	}

	public static void setKeyDown(Key key) {
		if (key == Key.NOT_FOUND) {
		} else {
			down[key.getKeyCode()] = true;
		}
	}

	public static void setKeyUp(Key key) {
		if (key == Key.NOT_FOUND) {
		} else {
			down[key.getKeyCode()] = false;
		}
	}
}
