package com.oroarmor.core.game.gui.text;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;

import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.VertexBufferLayout;

public class FontMeshCreator {

	// TODO: Only checking for width, does not check for height out of bounds
	public static Mesh createMesh(Font font, String text, float textSize, float width) {
		FloatBuffer verticies = BufferUtils.createFloatBuffer((2 + 2) * 4 * text.length());
		IntBuffer triangles = BufferUtils.createIntBuffer(3 * 2 * text.length());

		List<Line> lines = compileLines(font, text, textSize, width);

		linesToMeshData(lines, font, textSize, width, verticies, triangles);

		verticies.flip();
		triangles.flip();

		return new Mesh(verticies, triangles, new VertexBufferLayout().pushFloats(2).pushFloats(2));
	}

	private static void linesToMeshData(List<Line> lines, Font font, float textSize, float width, FloatBuffer verticies,
			IntBuffer triangles) {

		float cursorX = 0f;
		float cursorY = 0f;

		float spaceLength = (font.getCharacters()[32].xadvance - font.getMetaData().getPadding().getWidth())
				* font.getMetaData().getBase();
		spaceLength = 15;

		int index = 0;

		for (Line line : lines) {
			for (Word word : line.getWords()) {
				for (FontCharacter letter : word.getCharacters()) {
					addCoords(verticies, cursorX + (letter.xoffset * textSize), cursorY + (letter.yoffset * textSize), //

							cursorX + (letter.xoffset * textSize) + letter.width * textSize,
							cursorY + (letter.yoffset * textSize) + letter.height * textSize,

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

	private static List<Line> compileLines(Font font, String text, float textSize, float width) {
		List<Line> lines = new ArrayList<Line>(1);

		char[] characters = text.toCharArray();

		float spaceLength = (font.getCharacters()[32].xadvance - font.getMetaData().getPadding().getWidth())
				* font.getMetaData().getBase();
		spaceLength = 15;

		Line currentLine = new Line(width, spaceLength, textSize);

		Word currentWord = new Word(textSize);

		for (char c : characters) {
			int ascii = c;
			if (c == 32) {
				boolean added = currentLine.addWord(currentWord);
				if (!added) {
					lines.add(currentLine);
					currentLine = new Line(width, spaceLength, textSize);
					currentLine.addWord(currentWord);
				}
				currentWord = new Word(textSize);
				continue;
			}
			FontCharacter character = font.getCharacters()[ascii];
			currentWord.addCharacter(character);
		}

		boolean added = currentLine.addWord(currentWord);
		if (!added) {
			lines.add(currentLine);
			currentLine = new Line(width, spaceLength, textSize);
			currentLine.addWord(currentWord);
		}
		lines.add(currentLine);

		return lines;
	}

	public static void addCoords(FloatBuffer buffer, float x, float y, float maxX, float maxY, float textureX,
			float textureY, float textureMaxX, float textureMaxY) {
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

	private static void addTriangles(IntBuffer buffer, int index) {
		buffer.put(index);
		buffer.put(index + 1);
		buffer.put(index + 2);
		buffer.put(index + 2);
		buffer.put(index + 3);
		buffer.put(index);
	}

}
