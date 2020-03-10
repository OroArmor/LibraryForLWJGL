package com.oroarmor.core.game.gui.text;

import com.oroarmor.core.opengl.Texture;
import com.oroarmor.util.ResourceLoader;

public class FontLoader {

	private static int getDataFromToken(String token) {
		return Integer.parseInt(token.split("=")[1]);
	}

	public static Font loadFontDataFromFiles(String fontDataPath, String fontTexturePath) {
		return loadFontFromData(ResourceLoader.loadFile(Class.class.getResourceAsStream(fontDataPath)),
				new Texture(fontTexturePath));
	}

	public static Font loadFontFromData(String fontData, Texture fontTexture) {
		String[] fontLines = fontData.split("\n");

		FontCharacter[] characters = new FontCharacter[1];
		FontKerning kernings = new FontKerning();

		String fontName = "";
		int fontSize = 0;
		boolean bold = false;
		boolean italic = false;
		int lineHeight = 0;
		int base = 0;

		for (String line : fontLines) {

			String[] tokens = line.split(" +");

			switch (tokens[0]) {
			case "info":
				fontName = tokens[1].split("=")[1];
				fontSize = getDataFromToken(tokens[2]);
				bold = getDataFromToken(tokens[3]) == 1;
				italic = getDataFromToken(tokens[4]) == 1;
			case "common":
				lineHeight = getDataFromToken(tokens[1]);
				base = getDataFromToken(tokens[2]);
			case "chars":
				characters = new FontCharacter[getDataFromToken(tokens[1])];
			case "char":
				int charID = getDataFromToken(tokens[1]);
				characters[charID] = new FontCharacter(charID, getDataFromToken(tokens[2]), getDataFromToken(tokens[3]),
						getDataFromToken(tokens[4]), getDataFromToken(tokens[5]), getDataFromToken(tokens[6]),
						getDataFromToken(tokens[7]), getDataFromToken(tokens[8]));

			case "kerning":
				kernings.addKerning(characters[getDataFromToken(tokens[1])], characters[getDataFromToken(tokens[2])],
						getDataFromToken(tokens[3]));
			}

		}

		return new Font(characters, kernings, fontTexture,
				new FontMetaData(fontName, fontSize, bold, italic, lineHeight, base));
	}

}
