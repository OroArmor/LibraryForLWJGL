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

		this.compile();
	}

	public void setOrthoView(Matrix4f orthoView) {
		this.setUniformMat4f("u_V", orthoView);
	}

	public Shader setObjectModel(Matrix4f objectModel) {
		this.setUniformMat4f("u_M", objectModel);
		return this;
	}

	public TextureShader setTexture(Texture texture) {
		texture.bind(0);
		this.setUniform1i("u_Texture", 0);
		return this;
	}

	public Shader setZ(int i) {
		this.setUniform1f("u_Z", i);
		return this;
	}
}
