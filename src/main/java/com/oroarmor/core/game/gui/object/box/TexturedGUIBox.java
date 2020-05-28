package com.oroarmor.core.game.gui.object.box;

import com.oroarmor.core.game.gui.shader.GUIShaders;
import com.oroarmor.core.opengl.Renderer;
import com.oroarmor.core.opengl.Texture;

public class TexturedGUIBox extends GUIBox {

	private Texture texture;

	public TexturedGUIBox(final float x, final float y, final float width, final float height, final Texture texture) {
		super(x, y, width, height);
		setTexture(texture);
	}

	public Texture getTexture() {
		return texture;
	}

	@Override
	public void render(final Renderer renderer) {
		boxMesh.render(renderer,
				GUIShaders.getTextureShader().setTexture(texture).setObjectModel(animationMatrix));
	}

	public void setTexture(final Texture texture) {
		this.texture = texture;
	}

}
