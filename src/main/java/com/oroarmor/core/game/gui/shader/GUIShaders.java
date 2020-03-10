package com.oroarmor.core.game.gui.shader;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import com.oroarmor.core.game.gui.shader.solidcolor.SolidColorShader;

public class GUIShaders {

	private static SolidColorShader solidColorShader;

	public static SolidColorShader getSolidColorShader(Vector4f color) {
		if (solidColorShader == null) {
			solidColorShader = new SolidColorShader();
		}
		solidColorShader.setColor(color);
		return solidColorShader;
	}

	public static void updateShaderView(Matrix4f orthoView) {
		if (solidColorShader != null) {
			solidColorShader.setOrthoView(orthoView);
		}
	}

}
