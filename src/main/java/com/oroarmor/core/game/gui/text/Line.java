package com.oroarmor.core.game.gui.text;

import java.util.ArrayList;
import java.util.List;

public class Line {
	private float currentLength;
	private final float maxLength;

	private final float spaceWidth;

	private final List<Word> words = new ArrayList<>();

	public Line(final float maxLength, final float spaceWidth, final float fontSize) {
		this.maxLength = maxLength;
		this.spaceWidth = spaceWidth * fontSize;
	}

	public boolean addWord(final Word word) {
		double additionalLength = word.getWordWidth();
		additionalLength += !this.words.isEmpty() ? this.spaceWidth : 0;
		if (this.currentLength + additionalLength <= this.maxLength) {
			this.words.add(word);
			this.currentLength += additionalLength;
			return true;
		} else {
			return false;
		}
	}

	public float getLineLength() {
		return this.currentLength;
	}

	public float getMaxLength() {
		return this.maxLength;
	}

	public List<Word> getWords() {
		return this.words;
	}

	@Override
	public String toString() {
		return "Line [currentLength=" + this.currentLength + ", maxLength=" + this.maxLength + ", spaceWidth=" + this.spaceWidth
				+ ", words=" + this.words + "]";
	}
}
