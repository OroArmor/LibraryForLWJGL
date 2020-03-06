package com.oroarmor.core.game.gui.shader;

import org.joml.Vector4f;

import com.oroarmor.core.opengl.Shader;

public class GUIShaders {

	private static SolidColorShader solidColorShader = new SolidColorShader(null, null);

	public static Shader getSolidColorShader(Vector4f vector4f) {
		return solidColorShader;
	}

}
