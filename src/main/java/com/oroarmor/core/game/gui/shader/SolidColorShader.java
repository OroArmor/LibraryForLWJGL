package com.oroarmor.core.game.gui.shader;

import org.joml.Matrix4f;

import com.oroarmor.core.opengl.Shader;

public class SolidColorShader extends Shader {

	public SolidColorShader(String vertexSource, String fragmentSource) {
		super(vertexSource, fragmentSource);

		this.compile();
	}

	public void setOrthoModel(Matrix4f orthoModel) {

	}

}
