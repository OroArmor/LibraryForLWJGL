package com.oroarmor.core.game.gui.shader.solidcolor;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import com.oroarmor.core.opengl.Shader;
import com.oroarmor.util.ResourceLoader;

public class SolidColorShader extends Shader {

	public SolidColorShader() {
		super(//
				ResourceLoader.loadFile(
						Class.class.getResourceAsStream("/com/oroarmor/core/game/gui/shader/solidcolor/solidcolor.vs")),
				ResourceLoader.loadFile(Class.class
						.getResourceAsStream("/com/oroarmor/core/game/gui/shader/solidcolor/solidcolor.fs")));

		compile();
	}

	public void setColor(final Vector4f color) {
		this.setUniform4f("u_color", color);
	}

	public Shader setObjectModel(final Matrix4f objectModel) {
		setUniformMat4f("u_M", objectModel);
		return this;
	}

	public void setOrthoView(final Matrix4f orthoView) {
		setUniformMat4f("u_V", orthoView);
	}

	public Shader setZ(final int i) {
		setUniform1f("u_Z", i);
		return this;
	}
}
