package com.oroarmor.core.glfw.event.key;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_0;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_1;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_2;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_3;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_4;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_5;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_6;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_7;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_8;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_9;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_B;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_BACKSLASH;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_BACKSPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_C;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_COMMA;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_E;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_EQUAL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F11;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_G;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_GRAVE_ACCENT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_H;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_I;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_J;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_K;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_L;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_BRACKET;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_M;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_MINUS;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_N;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_O;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_P;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_PERIOD;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Q;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_R;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_BRACKET;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_SHIFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SEMICOLON;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SLASH;
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
	ONE(GLFW_KEY_1, '1', '!'), //
	TWO(GLFW_KEY_2, '2', '@'), //
	THREE(GLFW_KEY_3, '3', '#'), //
	FOUR(GLFW_KEY_4, '4', '$'), //
	FIVE(GLFW_KEY_5, '5', '%'), //
	SIX(GLFW_KEY_6, '6', '^'), //
	SEVEN(GLFW_KEY_7, '7', '&'), //
	EIGHT(GLFW_KEY_8, '8', '*'), //
	NINE(GLFW_KEY_9, '9', '('), //
	ZERO(GLFW_KEY_0, '0', ')'),
	//

	A(GLFW_KEY_A, 'a', 'A'), //
	B(GLFW_KEY_B, 'b', 'B'), //
	C(GLFW_KEY_C, 'c', 'C'), //
	D(GLFW_KEY_D, 'd', 'D'), //
	E(GLFW_KEY_E, 'e', 'E'), //
	F(GLFW_KEY_F, 'f', 'F'), //
	G(GLFW_KEY_G, 'g', 'G'), //
	H(GLFW_KEY_H, 'h', 'H'), //
	I(GLFW_KEY_I, 'i', 'I'), //
	J(GLFW_KEY_J, 'j', 'J'), //
	K(GLFW_KEY_K, 'k', 'K'), //
	L(GLFW_KEY_L, 'l', 'L'), //
	M(GLFW_KEY_M, 'm', 'M'), //
	N(GLFW_KEY_N, 'n', 'N'), //
	O(GLFW_KEY_O, 'o', 'O'), //
	P(GLFW_KEY_P, 'p', 'P'), //
	Q(GLFW_KEY_Q, 'q', 'Q'), //
	R(GLFW_KEY_R, 'r', 'R'), //
	S(GLFW_KEY_S, 's', 'S'), //
	T(GLFW_KEY_T, 't', 'T'), //
	U(GLFW_KEY_U, 'u', 'U'), //
	V(GLFW_KEY_V, 'v', 'V'), //
	W(GLFW_KEY_W, 'w', 'W'), //
	X(GLFW_KEY_X, 'x', 'X'), //
	Y(GLFW_KEY_Y, 'y', 'Y'), //
	Z(GLFW_KEY_Z, 'z', 'Z'), //
	//

	BACKSLASH(GLFW_KEY_BACKSLASH, '\\', '|'), //
	COMMA(GLFW_KEY_COMMA, ',', '<'), //
	EQUAL(GLFW_KEY_EQUAL, '=', '+'), //
	ESCAPE(GLFW_KEY_ESCAPE, '\0'), //
	GRAVE(GLFW_KEY_GRAVE_ACCENT, '`', '~'), //
	LEFT_BRACKET(GLFW_KEY_LEFT_BRACKET, '[', '{'), //
	MINUS(GLFW_KEY_MINUS, '-', '_'), //
	PERIOD(GLFW_KEY_PERIOD, '.', '>'), //
	RIGHT_BRACKET(GLFW_KEY_RIGHT_BRACKET, ']', '}'), //
	SEMICOLON(GLFW_KEY_SEMICOLON, ';', ':'), //
	SLASH(GLFW_KEY_SLASH, '/', '?'), //
	SPACE(GLFW_KEY_SPACE, ' '), //
	//

	F11(GLFW_KEY_F11, '\0'), //
	//

	DOWN(GLFW_KEY_DOWN, '\0'), //
	LEFT(GLFW_KEY_LEFT, '\0'), //
	LEFT_CONTROL(GLFW_KEY_LEFT_CONTROL, '\0'), //
	LEFT_SHIFT(GLFW_KEY_LEFT_SHIFT, '\0'), //
	NOT_FOUND(GLFW_KEY_UNKNOWN, '\0'), //
	RIGHT(GLFW_KEY_RIGHT, '\0'), //
	RIGHT_SHIFT(GLFW_KEY_RIGHT_SHIFT, '\0'), //
	UP(GLFW_KEY_UP, '\0'), //
	BACKSPACE(GLFW_KEY_BACKSPACE, (char) 8);
	//

	public static Key getKey(final int keyCode) {
		for (final Key key : values()) {
			if (key.getKeyCode() == keyCode) {
				return key;
			}
		}

		return NOT_FOUND;
	}

	private int keyCode;
	private char lower;
	private char upper;

	private Key(final int keyCode, final char key) {
		this(keyCode, key, key);
	}

	private Key(final int keyCode, final char lower, final char upper) {
		this.keyCode = keyCode;
		this.lower = lower;
		this.upper = upper;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public char getLowerChar() {
		return lower;
	}

	public char getUpperChar() {
		return upper;
	}

}