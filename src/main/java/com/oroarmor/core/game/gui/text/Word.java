package com.oroarmor.core.game.gui.text;

import java.util.ArrayList;
import java.util.List;

public class Word {

	private final List<FontCharacter> characters = new ArrayList<>(1);
	private final float fontSize;
	private float wordWidth;

	public Word(final float textSize) {
		this.fontSize = textSize;
	}

	public void addCharacter(final FontCharacter character) {
		this.characters.add(character);
		this.wordWidth += character.xadvance * this.fontSize;
	}

	public List<FontCharacter> getCharacters() {
		return this.characters;
	}

	public float getWordWidth() {
		return this.wordWidth;
	}

	@Override
	public String toString() {
		return "Word [characters=" + this.characters + ", fontSize=" + this.fontSize + ", wordWidth=" + this.wordWidth + "]";
	}

}
