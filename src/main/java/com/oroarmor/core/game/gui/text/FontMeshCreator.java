package com.oroarmor.core.game.gui.text;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.oroarmor.core.opengl.Mesh;

public class FontMeshCreator {

	public static Mesh createMesh(Font font, String text, float textSize) {

		return null;
	}

	public FloatBuffer addCoords(FloatBuffer buffer, float x, float y, float maxX, float maxY, float textureX,
			float textureY, float textureMaxX, float textureMaxY) {
		buffer.put(x);
		buffer.put(textureX);
		buffer.put(y);
		buffer.put(textureY);

		buffer.put(x);
		buffer.put(textureX);
		buffer.put(maxY);
		buffer.put(textureMaxY);

		buffer.put(maxX);
		buffer.put(textureMaxX);
		buffer.put(maxY);
		buffer.put(textureMaxY);

		buffer.put(maxX);
		buffer.put(textureMaxX);
		buffer.put(y);
		buffer.put(textureY);

		return buffer;
	}

	public IntBuffer addVertices(IntBuffer buffer, int index) {
		buffer.put(index);
		buffer.put(index + 1);
		buffer.put(index + 2);
		buffer.put(index + 2);
		buffer.put(index + 3);
		buffer.put(index);
		return buffer;
	}

}
