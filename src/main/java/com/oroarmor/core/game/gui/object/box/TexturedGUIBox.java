package com.oroarmor.core.game.gui.object.box;

import com.oroarmor.core.game.gui.shader.GUIShaders;
import com.oroarmor.core.opengl.Renderer;
import com.oroarmor.core.opengl.Texture;

public class TexturedGUIBox extends GUIBox {

	private Texture texture;

	public TexturedGUIBox(float x, float y, float width, float height, Texture texture) {
		super(x, y, width, height);
		this.setTexture(texture);
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	@Override
	public void render(Renderer renderer) {
		boxMesh.render(renderer, GUIShaders.getTextureShader().setTexture(texture).setObjectModel(animationMatrix));
	}

}
