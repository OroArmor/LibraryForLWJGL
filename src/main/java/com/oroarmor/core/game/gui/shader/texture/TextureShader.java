package com.oroarmor.core.game.gui.shader.texture;

import org.joml.Matrix4f;

import com.oroarmor.core.opengl.Shader;
import com.oroarmor.core.opengl.Texture;
import com.oroarmor.util.ResourceLoader;

public class TextureShader extends Shader {

	public TextureShader() {
		super(//
				ResourceLoader.loadFile(
						Class.class.getResourceAsStream("/com/oroarmor/core/game/gui/shader/texture/texture.vs")),
				ResourceLoader.loadFile(
						Class.class.getResourceAsStream("/com/oroarmor/core/game/gui/shader/texture/texture.fs")));

		compile();
	}

	public Shader setObjectModel(final Matrix4f objectModel) {
		setUniformMat4f("u_M", objectModel);
		return this;
	}

	public void setOrthoView(final Matrix4f orthoView) {
		setUniformMat4f("u_V", orthoView);
	}

	public TextureShader setTexture(final Texture texture) {
		texture.bind(0);
		setUniform1i("u_Texture", 0);
		return this;
	}

	public Shader setZ(final int i) {
		setUniform1f("u_Z", i);
		return this;
	}
}
