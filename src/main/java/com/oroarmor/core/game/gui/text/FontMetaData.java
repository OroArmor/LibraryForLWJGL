package com.oroarmor.core.game.gui.text;

public class FontMetaData {
    private final int base;
    private final boolean bold;
    private final String fontName;
    private final int fontSize;
    private final boolean italic;
    private final int lineHeight;
    private final Padding padding;
    public FontMetaData(final String fontName, final int fontSize, final boolean bold, final boolean italic,
                        final int lineHeight, final int base, final Padding padding) {
        this.fontName = fontName;
        this.fontSize = fontSize;
        this.bold = bold;
        this.italic = italic;
        this.lineHeight = lineHeight;
        this.base = base;
        this.padding = padding;
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

    public Padding getPadding() {
        return padding;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public static class Padding {
        float width;
        float height;

        public Padding(final float width, final float height) {
            this.width = width;
            this.height = height;
        }

        public float getHeight() {
            return height;
        }

        public float getWidth() {
            return width;
        }

    }

}
