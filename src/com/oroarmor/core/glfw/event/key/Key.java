package com.oroarmor.core.glfw.event.key;

import static org.lwjgl.glfw.GLFW.*;

public enum Key {
	A(GLFW_KEY_A), B(GLFW_KEY_B), C(GLFW_KEY_C), D(GLFW_KEY_D), DOWN(GLFW_KEY_DOWN), E(GLFW_KEY_E), F(GLFW_KEY_F),
	G(GLFW_KEY_G), H(GLFW_KEY_H), I(GLFW_KEY_I), J(GLFW_KEY_J), K(GLFW_KEY_K), L(GLFW_KEY_L), LEFT(GLFW_KEY_LEFT),
	M(GLFW_KEY_M), N(GLFW_KEY_N), O(GLFW_KEY_O), P(GLFW_KEY_P), Q(GLFW_KEY_Q), R(GLFW_KEY_R), RIGHT(GLFW_KEY_RIGHT),
	S(GLFW_KEY_S), SPACE(GLFW_KEY_SPACE), T(GLFW_KEY_T), U(GLFW_KEY_U), UP(GLFW_KEY_UP), V(GLFW_KEY_V), W(GLFW_KEY_W),
	X(GLFW_KEY_X), Y(GLFW_KEY_Y), Z(GLFW_KEY_Z), NOT_FOUND(GLFW_KEY_UNKNOWN), ESCAPE(GLFW_KEY_ESCAPE),
	LEFT_SHIFT(GLFW_KEY_LEFT_SHIFT), RIGHT_SHIFT(GLFW_KEY_RIGHT_SHIFT), PERIOD(GLFW_KEY_PERIOD),
	LEFT_CONTROL(GLFW_KEY_LEFT_CONTROL);

	private int keyCode;

	private Key(int keyCode) {
		this.keyCode = keyCode;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public static Key getKey(int keyCode) {
		for (Key key : values()) {
			if (key.getKeyCode() == keyCode) {
				return key;
			}
		}

		return NOT_FOUND;
	}

}