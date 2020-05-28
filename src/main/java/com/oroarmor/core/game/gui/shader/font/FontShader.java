package com.oroarmor.core.game.gui.shader.font;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import com.oroarmor.core.opengl.Shader;
import com.oroarmor.core.opengl.Texture;
import com.oroarmor.util.ResourceLoader;

public class FontShader extends Shader {

	public FontShader() {
		super(//
				ResourceLoader
						.loadFile(Class.class.getResourceAsStream("/com/oroarmor/core/game/gui/shader/font/font.vs")),
				ResourceLoader
						.loadFile(Class.class.getResourceAsStream("/com/oroarmor/core/game/gui/shader/font/font.fs")));

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

	public void setTexture(final Texture texture) {
		texture.bind(1);
		setUniform1i("u_Texture", 1);
	}

	public Shader setZ(final int i) {
		setUniform1f("u_Z", i);
		return this;
	}
}
