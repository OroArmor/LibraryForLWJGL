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

	public FontCharacter(int character, int x, int y, int width, int height, int xoffset, int yoffset, int xadvance) {
		this.character = character;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xoffset = xoffset;
		this.yoffset = yoffset;
		this.xadvance = xadvance;
	}
}
