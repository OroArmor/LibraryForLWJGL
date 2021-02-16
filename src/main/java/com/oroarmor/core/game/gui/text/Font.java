package com.oroarmor.core.game.gui.text;

import java.util.HashMap;
import java.util.Map;

import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.Texture;

public class Font {
    private final FontCharacter[] characters;
    private final FontKerning kernings;
    private final FontMetaData metaData;
    private final Texture texture;
    private Map<TextSizePair, Mesh> fontMeshes;

    public Font(FontCharacter[] characters, FontKerning kernings, Texture texture, FontMetaData fontMetaData) {
        this.characters = characters;
        this.kernings = kernings;
        this.texture = texture;
        metaData = fontMetaData;
    }

    public FontCharacter[] getCharacters() {
        return characters;
    }

    public FontKerning getKernings() {
        return kernings;
    }

    public FontMetaData getMetaData() {
        return metaData;
    }

    public Mesh getTextMesh(String text, float textSize, float textWidth) {
        if (fontMeshes == null) {
            fontMeshes = new HashMap<>();
        }

        TextSizePair textSizePair = new TextSizePair(text, textSize);

        if (fontMeshes.containsKey(textSizePair)) {
            return fontMeshes.get(textSizePair);
        }

        Mesh newTextMesh = FontMeshCreator.createMesh(this, text, textSize, textWidth);

        fontMeshes.put(textSizePair, newTextMesh);

        return newTextMesh;
    }

    public Texture getTexture() {
        return texture;
    }

    private static class TextSizePair {
        float size;
        String text;

        public TextSizePair(String text, float size) {
            this.text = text;
            this.size = size;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof TextSizePair) {
                return ((TextSizePair) obj).text.equals(text) && ((TextSizePair) obj).size == size;
            }

            return false;
        }
    }
}
