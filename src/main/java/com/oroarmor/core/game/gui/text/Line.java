package com.oroarmor.core.game.gui.text;

import java.util.ArrayList;
import java.util.List;

public class Line {
	private float currentLength;
	private float maxLength;

	private float spaceWidth;

	private List<Word> words = new ArrayList<Word>();

	public Line(float maxLength, float spaceWidth, float fontSize) {
		this.maxLength = maxLength;
		this.spaceWidth = spaceWidth * fontSize;
	}

	public boolean addWord(Word word) {
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
