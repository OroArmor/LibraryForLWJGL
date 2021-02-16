package com.oroarmor.core.game.gui.text;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private final float maxLength;
    private final float spaceWidth;
    private final List<Word> words = new ArrayList<>();
    private float currentLength;

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
        }
        return false;
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
