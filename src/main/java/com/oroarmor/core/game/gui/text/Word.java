package com.oroarmor.core.game.gui.text;

import java.util.ArrayList;
import java.util.List;

public class Word {

	private final List<FontCharacter> characters = new ArrayList<>(1);
	private final float fontSize;
	private float wordWidth;

	public Word(final float textSize) {
		fontSize = textSize;
	}

	public void addCharacter(final FontCharacter character) {
		characters.add(character);
		wordWidth += character.xadvance * fontSize;
	}

	public List<FontCharacter> getCharacters() {
		return characters;
	}

	public float getWordWidth() {
		return wordWidth;
	}

	@Override
	public String toString() {
		return "Word [characters=" + characters + ", fontSize=" + fontSize + ", wordWidth=" + wordWidth + "]";
	}

}
