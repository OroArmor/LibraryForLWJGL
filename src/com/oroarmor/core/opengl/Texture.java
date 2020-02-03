package com.oroarmor.core.opengl;

import java.nio.ByteBuffer;

import org.lwjgl.stb.STBImage;

import com.oroarmor.core.Bindable;
import com.oroarmor.core.Destructable;
import com.oroarmor.core.Destructor;
import com.oroarmor.util.TextureLoader;
import static org.lwjgl.opengl.GL45.*;

public class Texture implements Bindable, Destructable {
	private int textureID;
	private String filePath;

	private int width, height;

	private int slot = 0;

	public Texture(String filePath) {
		this.filePath = filePath;

		int[] x = new int[1];
		int[] y = new int[1];
		int[] channels_in_file = new int[1];
		int desired_channels = 4;

		STBImage.stbi_set_flip_vertically_on_load(true);

		ByteBuffer buffer = STBImage.stbi_load(filePath, x, y, channels_in_file, desired_channels);

		this.width = x[0];
		this.height = y[0];

		this.textureID = TextureLoader.loadTexture(buffer, width, height);
		Destructor.addDestructable(this);
	}

	@Override
	public void bind() {
		glActiveTexture(GL_TEXTURE0 + slot);
		glBindTexture(GL_TEXTURE_2D, textureID);
	}

	public void bind(int slot) {
		this.slot = Math.min(slot, 31);
		bind();
	}

	@Override
	public void destroy() {
		glDeleteTextures(textureID);
	}

	public String getFilePath() {
		return filePath;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
}
