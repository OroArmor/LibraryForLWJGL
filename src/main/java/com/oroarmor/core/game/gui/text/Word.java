package com.oroarmor.core.game.gui.text;

import java.util.ArrayList;
import java.util.List;

public class Word {

	private List<FontCharacter> characters = new ArrayList<FontCharacter>(1);
	private float fontSize;
	private float wordWidth;

	public Word(float textSize) {
		this.fontSize = textSize;
	}

	public void addCharacter(FontCharacter character) {
		characters.add(character);
		this.wordWidth += character.xadvance * fontSize;
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
