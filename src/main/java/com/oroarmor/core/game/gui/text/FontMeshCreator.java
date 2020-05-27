package com.oroarmor.core.game.gui.text;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;

import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.VertexBufferLayout;

public class FontMeshCreator {

	public static void addCoords(final FloatBuffer buffer, final float x, final float y, final float maxX, final float maxY, final float textureX,
			final float textureY, final float textureMaxX, final float textureMaxY) {
		buffer.put(x);
		buffer.put(y);
		buffer.put(textureX / 512);
		buffer.put(textureY / 512);

		buffer.put(maxX);
		buffer.put(y);
		buffer.put(textureMaxX / 512);
		buffer.put(textureY / 512);

		buffer.put(maxX);
		buffer.put(maxY);
		buffer.put(textureMaxX / 512);
		buffer.put(textureMaxY / 512);

		buffer.put(x);
		buffer.put(maxY);
		buffer.put(textureX / 512);
		buffer.put(textureMaxY / 512);
	}

	private static void addTriangles(final IntBuffer buffer, final int index) {
		buffer.put(index);
		buffer.put(index + 1);
		buffer.put(index + 2);
		buffer.put(index + 2);
		buffer.put(index + 3);
		buffer.put(index);
	}

	private static List<Line> compileLines(final Font font, final String text, final float textSize, final float width) {
		final List<Line> lines = new ArrayList<>(1);

		final char[] characters = text.toCharArray();

		float spaceLength = (font.getCharacters()[32].xadvance - font.getMetaData().getPadding().getWidth())
				* font.getMetaData().getBase();
		spaceLength = 15;

		Line currentLine = new Line(width, spaceLength, textSize);

		Word currentWord = new Word(textSize);

		for (final char c : characters) {
			final int ascii = c;
			if (c == 32) {
				final boolean added = currentLine.addWord(currentWord);
				if (!added) {
					lines.add(currentLine);
					currentLine = new Line(width, spaceLength, textSize);
					currentLine.addWord(currentWord);
				}
				currentWord = new Word(textSize);
				continue;
			}
			final FontCharacter character = font.getCharacters()[ascii];
			currentWord.addCharacter(character);
		}

		final boolean added = currentLine.addWord(currentWord);
		if (!added) {
			lines.add(currentLine);
			currentLine = new Line(width, spaceLength, textSize);
			currentLine.addWord(currentWord);
		}
		lines.add(currentLine);

		return lines;
	}

	// TODO: Only checking for width, does not check for height out of bounds
	public static Mesh createMesh(final Font font, final String text, final float textSize, final float width) {
		final FloatBuffer verticies = BufferUtils.createFloatBuffer((2 + 2) * 4 * text.length());
		final IntBuffer triangles = BufferUtils.createIntBuffer(3 * 2 * text.length());

		final List<Line> lines = compileLines(font, text, textSize, width);

		linesToMeshData(lines, font, textSize, width, verticies, triangles);

		verticies.flip();
		triangles.flip();

		return new Mesh(verticies, triangles, new VertexBufferLayout().pushFloats(2).pushFloats(2));
	}

	private static void linesToMeshData(final List<Line> lines, final Font font, final float textSize, final float width, final FloatBuffer verticies,
			final IntBuffer triangles) {

		float cursorX = 0f;
		float cursorY = 0f;

		float spaceLength = (font.getCharacters()[32].xadvance - font.getMetaData().getPadding().getWidth())
				* font.getMetaData().getBase();
		spaceLength = 15;

		int index = 0;

		for (final Line line : lines) {
			for (final Word word : line.getWords()) {
				for (final FontCharacter letter : word.getCharacters()) {
					addCoords(verticies, cursorX + letter.xoffset * textSize, cursorY + letter.yoffset * textSize, //

							cursorX + letter.xoffset * textSize + letter.width * textSize,
							cursorY + letter.yoffset * textSize + letter.height * textSize,

							letter.x, letter.y, //
							letter.x + letter.width, letter.y + letter.height);//

					addTriangles(triangles, index);
					index += 4;
					cursorX += letter.xadvance * textSize;
				}
				cursorX += spaceLength * textSize;
			}
			cursorX = 0;
			cursorY += font.getMetaData().getLineHeight() * textSize;
		}

	}

}
