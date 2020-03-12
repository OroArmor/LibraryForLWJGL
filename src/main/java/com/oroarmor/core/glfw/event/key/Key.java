package com.oroarmor.core.glfw.event.key;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_B;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_BACKSPACE;
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
	A(GLFW_KEY_A, 'a'), B(GLFW_KEY_B, 'b'), C(GLFW_KEY_C, 'c'), D(GLFW_KEY_D, 'd'), DOWN(GLFW_KEY_DOWN, '\0'),
	E(GLFW_KEY_E, 'e'), ESCAPE(GLFW_KEY_ESCAPE, '\0'), F(GLFW_KEY_F, 'f'), F11(GLFW_KEY_F11, '\0'), G(GLFW_KEY_G, 'g'),
	H(GLFW_KEY_H, 'h'), I(GLFW_KEY_I, 'i'), J(GLFW_KEY_J, 'j'), K(GLFW_KEY_K, 'k'), L(GLFW_KEY_L, 'l'),
	LEFT(GLFW_KEY_LEFT, '\0'), LEFT_CONTROL(GLFW_KEY_LEFT_CONTROL, '\0'), LEFT_SHIFT(GLFW_KEY_LEFT_SHIFT, '\0'),
	M(GLFW_KEY_M, 'm'), N(GLFW_KEY_N, 'n'), NOT_FOUND(GLFW_KEY_UNKNOWN, '\0'), O(GLFW_KEY_O, 'o'), P(GLFW_KEY_P, 'p'),
	PERIOD(GLFW_KEY_PERIOD, '.'), Q(GLFW_KEY_Q, 'q'), R(GLFW_KEY_R, 'r'), RIGHT(GLFW_KEY_RIGHT, '\0'),
	RIGHT_SHIFT(GLFW_KEY_RIGHT_SHIFT, '\0'), S(GLFW_KEY_S, 's'), SPACE(GLFW_KEY_SPACE, ' '), T(GLFW_KEY_T, 't'),
	U(GLFW_KEY_U, 'u'), UP(GLFW_KEY_UP, '\0'), V(GLFW_KEY_V, 'v'), W(GLFW_KEY_W, 'w'), X(GLFW_KEY_X, 'x'),
	Y(GLFW_KEY_Y, 'y'), Z(GLFW_KEY_Z, 'z'), BACKSPACE(GLFW_KEY_BACKSPACE, (char) 8);

	public static Key getKey(int keyCode) {
		for (Key key : values()) {
			if (key.getKeyCode() == keyCode) {
				return key;
			}
		}

		return NOT_FOUND;
	}

	private int keyCode;
	private char c;

	private Key(int keyCode, char c) {
		this.keyCode = keyCode;
		this.c = c;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public char getChar() {
		return c;
	}

}