package com.oroarmor.core.game.gui.shader;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import com.oroarmor.core.opengl.Shader;
import com.oroarmor.util.ResourceLoader;

public class SolidColorShader extends Shader {

	public SolidColorShader() {
		super(ResourceLoader
				.loadFile(Class.class.getResourceAsStream("/com/oroarmor/core/game/gui/shader/solidcolor.vs")),
				ResourceLoader
						.loadFile(Class.class.getResourceAsStream("/com/oroarmor/core/game/gui/shader/solidcolor.fs")));

		this.compile();
	}

	public void setOrthoView(Matrix4f orthoView) {
		this.setUniformMat4f("u_V", orthoView);
	}

	public Shader setObjectModel(Matrix4f objectModel) {
		this.setUniformMat4f("u_M", objectModel);
		return this;
	}

	public void setColor(Vector4f color) {
		this.setUniform4f("u_color", color);
	}
}
