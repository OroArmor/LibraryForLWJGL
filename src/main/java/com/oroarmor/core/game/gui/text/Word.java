package com.oroarmor.core.game.gui.text;

import java.util.ArrayList;
import java.util.List;

public class Word {

	private List<FontCharacter> characters;
	private int fontSize;
	private float wordWidth;

	public Word(int fontSize) {
		this.fontSize = fontSize;
	}

	public void addCharacter(FontCharacter character) {
		if (characters == null) {
			characters = new ArrayList<FontCharacter>(1);
		}

		characters.add(character);
		this.wordWidth += character.xadvance * (float) fontSize;
	}

	public List<FontCharacter> getCharacters() {
		return characters;
	}

	public float getWordWidth() {
		return wordWidth;
	}

}
