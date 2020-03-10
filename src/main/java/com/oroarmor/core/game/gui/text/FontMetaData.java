package com.oroarmor.core.game.gui.text;

public class FontMetaData {
	private final int base;
	private final boolean bold;
	private final String fontName;
	private final int fontSize;
	private final boolean italic;
	private final int lineHeight;

	public FontMetaData(String fontName, int fontSize, boolean bold, boolean italic, int lineHeight, int base) {
		this.fontName = fontName;
		this.fontSize = fontSize;
		this.bold = bold;
		this.italic = italic;
		this.lineHeight = lineHeight;
		this.base = base;
	}

	public int getBase() {
		return base;
	}

	public String getFontName() {
		return fontName;
	}

	public int getFontSize() {
		return fontSize;
	}

	public int getLineHeight() {
		return lineHeight;
	}

	public boolean isBold() {
		return bold;
	}

	public boolean isItalic() {
		return italic;
	}

}
