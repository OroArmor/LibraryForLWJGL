package com.oroarmor.core.game.gui.text;

import com.oroarmor.core.game.gui.text.FontMetaData.Padding;
import com.oroarmor.core.opengl.Texture;
import com.oroarmor.util.ResourceLoader;

public class FontLoader {

	private static int getDataFromToken(final String token) {
		return Integer.parseInt(token.split("=")[1].trim());
	}

	public static Font loadFontDataFromFiles(final String fontDataPath, final String fontTexturePath) {
		return loadFontFromData(ResourceLoader.loadFile(Class.class.getResourceAsStream(fontDataPath)),
				new Texture(fontTexturePath));
	}

	public static Font loadFontFromData(final String fontData, final Texture fontTexture) {
		final String[] fontLines = fontData.split("\n+");

		FontCharacter[] characters = new FontCharacter[1];
		final FontKerning kernings = new FontKerning();

		String fontName = "";
		int fontSize = 0;
		boolean bold = false;
		boolean italic = false;
		int lineHeight = 0;
		int base = 0;
		Padding padding = new Padding(0, 0);

		for (final String line : fontLines) {
			final String[] tokens = line.split(" +");

			switch (tokens[0]) {
			case "info":
				fontName = tokens[1].split("=")[1];
				fontSize = getDataFromToken(tokens[2]);
				bold = getDataFromToken(tokens[3]) == 1;
				italic = getDataFromToken(tokens[4]) == 1;
				padding = setPadding(tokens[10]);
				break;
			case "common":
				lineHeight = getDataFromToken(tokens[1]);
				base = getDataFromToken(tokens[2]);
				break;
			case "chars":
				characters = new FontCharacter[127]; // TODO: Make this read the last value of the characters first
														// (hard coded is fine for now)
				break;
			case "char":
				final int charID = getDataFromToken(tokens[1]);
				characters[charID] = new FontCharacter(charID, getDataFromToken(tokens[2]), getDataFromToken(tokens[3]),
						getDataFromToken(tokens[4]), getDataFromToken(tokens[5]), getDataFromToken(tokens[6]),
						getDataFromToken(tokens[7]), getDataFromToken(tokens[8]));
				break;
			case "kerning":
				kernings.addKerning(characters[getDataFromToken(tokens[1])], characters[getDataFromToken(tokens[2])],
						getDataFromToken(tokens[3]));
				break;
			}

		}

		return new Font(characters, kernings, fontTexture,
				new FontMetaData(fontName, fontSize, bold, italic, lineHeight, base, padding));
	}

	private static Padding setPadding(final String string) {
		final String[] split = string.split("=")[1].split(",");
		return new Padding(Integer.parseInt(split[1]) + Integer.parseInt(split[3]),
				Integer.parseInt(split[0]) + Integer.parseInt(split[2]));
	}

}
