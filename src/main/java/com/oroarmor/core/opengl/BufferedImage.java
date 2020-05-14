package com.oroarmor.core.opengl;

import java.nio.ByteBuffer;

import org.lwjgl.stb.STBImage;

public class BufferedImage extends Texture {

	/**
	 * The bytes for the image
	 */
	protected ByteBuffer imageBuffer;

	public BufferedImage(String filePath) {
		super(filePath);

		int[] x = new int[1];
		int[] y = new int[1];
		int[] channels_in_file = new int[1];
		int desired_channels = 4;

		imageBuffer = STBImage.stbi_load(filePath, x, y, channels_in_file, desired_channels);

		System.out.println(this.getR(0, 255) & 0xFF);
		System.out.println(this.getG(0, 255) & 0xFF);
		System.out.println(this.getB(0, 255) & 0xFF);
		System.out.println(this.getA(0, 255) & 0xFF);
	}

	/**
	 * 
	 * @return The byte for the image
	 */
	public ByteBuffer getImageBuffer() {
		return imageBuffer;
	}

	/**
	 * Gets the value of the red channel
	 * 
	 * @param x x-coord of the pixel
	 * @param y y-coord of the pixel
	 * @return The value of the red channel
	 */
	public byte getR(int x, int y) {
		return imageBuffer.get(x + y * height + 0);
	}

	/**
	 * Gets the value of the green channel
	 * 
	 * @param x x-coord of the pixel
	 * @param y y-coord of the pixel
	 * @return The value of the green channel
	 */
	public byte getG(int x, int y) {
		return imageBuffer.get(x + y * height + 1);
	}

	/**
	 * Gets the value of the blue channel
	 * 
	 * @param x x-coord of the pixel
	 * @param y y-coord of the pixel
	 * @return The value of the blue channel
	 */
	public byte getB(int x, int y) {
		return imageBuffer.get(x + y * height + 2);
	}

	/**
	 * Gets the value of the alpha channel
	 * 
	 * @param x x-coord of the pixel
	 * @param y y-coord of the pixel
	 * @return The value of the alpha channel
	 */
	public byte getA(int x, int y) {
		return imageBuffer.get(x + y * height + 3);
	}
}