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
				return ((TextSizePair) obj).text.equals(text) && ((TextSizePair) obj).size == size;
			}

			return false;
		}

	}

	private final FontCharacter[] characters;
	private Map<TextSizePair, Mesh> fontMeshes;
	private final FontKerning kernings;
	private final FontMetaData metaData;

	private final Texture texture;

	public Font(final FontCharacter[] characters, final FontKerning kernings, final Texture texture,
			final FontMetaData fontMetaData) {
		this.characters = characters;
		this.kernings = kernings;
		this.texture = texture;
		metaData = fontMetaData;
	}

	public FontCharacter[] getCharacters() {
		return characters;
	}

	public FontKerning getKernings() {
		return kernings;
	}

	public FontMetaData getMetaData() {
		return metaData;
	}

	public Mesh getTextMesh(final String text, final float textSize, final float textWidth) {
		if (fontMeshes == null) {
			fontMeshes = new HashMap<>();
		}

		final TextSizePair textSizePair = new TextSizePair(text, textSize);

		if (fontMeshes.containsKey(textSizePair)) {
			return fontMeshes.get(textSizePair);
		}

		final Mesh newTextMesh = FontMeshCreator.createMesh(this, text, textSize, textWidth);

		fontMeshes.put(textSizePair, newTextMesh);

		return newTextMesh;
	}

	public Texture getTexture() {
		return texture;
	}

}
