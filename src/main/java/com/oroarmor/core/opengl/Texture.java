package com.oroarmor.core.opengl;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDeleteTextures;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import java.nio.ByteBuffer;

import org.lwjgl.stb.STBImage;

import com.oroarmor.core.Bindable;
import com.oroarmor.core.Destructable;
import com.oroarmor.core.Destructor;
import com.oroarmor.util.TextureLoader;

/**
 * A class that represents a texture in OpenGL
 * 
 * @author OroArmor
 *
 */
public class Texture implements Bindable, Destructable {
	/**
	 * Path to the image texture
	 */
	protected String filePath;

	/**
	 * The height of the image
	 */
	protected int height;

	/**
	 * The slot to bind the texture to, default is 0
	 */
	protected int slot = 0;

	/**
	 * OpenGL texture id
	 */
	protected int textureID;

	/**
	 * The width of the image
	 */
	protected int width;

	/**
	 * Creates a new {@link Texture} based on a path to an image
	 * 
	 * @param filePath The path to the image texture
	 */
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
		STBImage.stbi_image_free(buffer);

		Destructor.addDestructable(this);
	}

	/**
	 * For subclasses
	 */
	protected Texture() {
	}

	/**
	 * Binds the texture to the previously set slot (or default if not set)
	 */
	@Override
	public void bind() {
		glActiveTexture(GL_TEXTURE0 + slot);
		glBindTexture(GL_TEXTURE_2D, textureID);
	}

	/**
	 * Bind the {@link Texture} to a certain slot
	 * 
	 * @param slot Slot to bind the texture to
	 */
	public void bind(int slot) {
		this.slot = Math.min(slot, 31);
		bind();
	}

	@Override
	public void destroy() {
		glDeleteTextures(textureID);
	}

	/**
	 * 
	 * @return The path to the image of the texture
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * 
	 * @return The height of the texture
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 
	 * @return The width of the image
	 */
	public int getWidth() {
		return width;
	}

	@Override
	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	/**
	 * 
	 * @return The slot that the texture is bound to currently
	 */
	public int getSlot() {
		return this.slot;
	}
}
