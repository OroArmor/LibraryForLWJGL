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
		additionalLength += !words.isEmpty() ? spaceWidth : 0;
		if (currentLength + additionalLength <= maxLength) {
			words.add(word);
			currentLength += additionalLength;
			return true;
		} else {
			return false;
		}
	}

	public float getLineLength() {
		return currentLength;
	}

	public float getMaxLength() {
		return maxLength;
	}

	public List<Word> getWords() {
		return words;
	}

	@Override
	public String toString() {
		return "Line [currentLength=" + currentLength + ", maxLength=" + maxLength + ", spaceWidth=" + spaceWidth
				+ ", words=" + words + "]";
	}
}
