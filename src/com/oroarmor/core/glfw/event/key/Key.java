package com.oroarmor.core.glfw.event.key;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_B;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_C;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_E;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F11;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_G;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_H;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_I;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_J;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_K;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_L;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_M;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_N;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_O;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_P;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_PERIOD;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Q;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_R;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_SHIFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_T;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_U;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UNKNOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_V;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_X;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Y;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Z;

public enum Key {
	A(GLFW_KEY_A), B(GLFW_KEY_B), C(GLFW_KEY_C), D(GLFW_KEY_D), DOWN(GLFW_KEY_DOWN), E(GLFW_KEY_E), ESCAPE(GLFW_KEY_ESCAPE),
	F(GLFW_KEY_F), F11(GLFW_KEY_F11), G(GLFW_KEY_G), H(GLFW_KEY_H), I(GLFW_KEY_I), J(GLFW_KEY_J), K(GLFW_KEY_K),
	L(GLFW_KEY_L), LEFT(GLFW_KEY_LEFT), LEFT_CONTROL(GLFW_KEY_LEFT_CONTROL), LEFT_SHIFT(GLFW_KEY_LEFT_SHIFT), M(GLFW_KEY_M), N(GLFW_KEY_N), NOT_FOUND(GLFW_KEY_UNKNOWN),
	O(GLFW_KEY_O), P(GLFW_KEY_P), PERIOD(GLFW_KEY_PERIOD), Q(GLFW_KEY_Q), R(GLFW_KEY_R), RIGHT(GLFW_KEY_RIGHT), RIGHT_SHIFT(GLFW_KEY_RIGHT_SHIFT),
	S(GLFW_KEY_S), SPACE(GLFW_KEY_SPACE), T(GLFW_KEY_T), U(GLFW_KEY_U), UP(GLFW_KEY_UP),
	V(GLFW_KEY_V), W(GLFW_KEY_W), X(GLFW_KEY_X),
	Y(GLFW_KEY_Y), Z(GLFW_KEY_Z);

	public static Key getKey(int keyCode) {
		for (Key key : values()) {
			if (key.getKeyCode() == keyCode) {
				return key;
			}
		}

		return NOT_FOUND;
	}

	private int keyCode;

	private Key(int keyCode) {
		this.keyCode = keyCode;
	}

	public int getKeyCode() {
		return keyCode;
	}

}