package com.oroarmor.core.game.gui.text;

import java.util.HashMap;
import java.util.Map;

import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.Texture;

public class Font {

	private static class TextSizePair {
		float size;
		String text;

		public TextSizePair(final String text, final float size) {
			this.text = text;
			this.size = size;
		}

		@Override
		public boolean equals(final Object obj) {
			if (obj instanceof TextSizePair) {
				return ((TextSizePair) obj).text.equals(this.text) && ((TextSizePair) obj).size == this.size;
			}

			return false;
		}

	}

	private final FontCharacter[] characters;
	private Map<TextSizePair, Mesh> fontMeshes;
	private final FontKerning kernings;
	private final FontMetaData metaData;

	private final Texture texture;

	public Font(final FontCharacter[] characters, final FontKerning kernings, final Texture texture, final FontMetaData fontMetaData) {
		this.characters = characters;
		this.kernings = kernings;
		this.texture = texture;
		this.metaData = fontMetaData;
	}

	public FontCharacter[] getCharacters() {
		return this.characters;
	}

	public FontKerning getKernings() {
		return this.kernings;
	}

	public FontMetaData getMetaData() {
		return this.metaData;
	}

	public Mesh getTextMesh(final String text, final float textSize, final float textWidth) {
		if (this.fontMeshes == null) {
			this.fontMeshes = new HashMap<>();
		}

		final TextSizePair textSizePair = new TextSizePair(text, textSize);

		if (this.fontMeshes.containsKey(textSizePair)) {
			return this.fontMeshes.get(textSizePair);
		}

		final Mesh newTextMesh = FontMeshCreator.createMesh(this, text, textSize, textWidth);

		this.fontMeshes.put(textSizePair, newTextMesh);

		return newTextMesh;
	}

	public Texture getTexture() {
		return this.texture;
	}

}
