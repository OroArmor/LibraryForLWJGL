package com.oroarmor.core.game.gui.text;

public class FontCharacter {
    public int character;
    public int height;
    public int width;
    public int x;
    public int xadvance;
    public int xoffset;
    public int y;
    public int yoffset;

    public FontCharacter(final int character, final int x, final int y, final int width, final int height,
                         final int xoffset, final int yoffset, final int xadvance) {
        this.character = character;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xoffset = xoffset;
        this.yoffset = yoffset;
        this.xadvance = xadvance;
    }

    @Override
    public String toString() {
        return "FontCharacter [character=" + (char) character + ", height=" + height + ", width=" + width + ", x=" + x
                + ", xadvance=" + xadvance + ", xoffset=" + xoffset + ", y=" + y + ", yoffset=" + yoffset + "]";
    }

}
