package com.oroarmor.core.opengl;

import java.nio.ByteBuffer;

import org.lwjgl.stb.STBImage;

public class BufferedImage {

	/**
	 * The bytes for the image
	 */
	protected ByteBuffer imageBuffer;

	/**
	 * The height of the image
	 */
	protected int height;

	/**
	 * The width of the image
	 */
	protected int width;

	/**
	 *
	 */
	protected int channels;

	public BufferedImage(final String filePath) {

		final int[] x = new int[1];
		final int[] y = new int[1];
		final int[] channels_in_file = new int[1];
		final int desired_channels = 3;

		this.imageBuffer = STBImage.stbi_load(filePath, x, y, channels_in_file, desired_channels);

		System.out.println(channels_in_file[0]);

		this.channels = channels_in_file[0];

		this.width = x[0];
		this.height = y[0];

		System.out.println(this.width);

		System.out.println(this.getR(0, 0) & 0xFF);
		System.out.println(this.getG(0, 0) & 0xFF);
		System.out.println(this.getB(0, 0) & 0xFF);
		System.out.println(this.getA(0, 0) & 0xFF);
	}

	/**
	 * Gets the value of the alpha channel
	 *
	 * @param x x-coord of the pixel
	 * @param y y-coord of the pixel
	 * @return The value of the alpha channel
	 */
	public byte getA(final int x, final int y) {
		if (this.channels < 4) {
			return 0;
		}

		return this.imageBuffer.get(this.channels * (x + y * this.height) + 3);
	}

	/**
	 * Gets the value of the blue channel
	 *
	 * @param x x-coord of the pixel
	 * @param y y-coord of the pixel
	 * @return The value of the blue channel
	 */
	public byte getB(final int x, final int y) {
		return this.imageBuffer.get(this.channels * (x + y * this.height) + 2);
	}

	/**
	 * Gets the value of the green channel
	 *
	 * @param x x-coord of the pixel
	 * @param y y-coord of the pixel
	 * @return The value of the green channel
	 */
	public byte getG(final int x, final int y) {
		return this.imageBuffer.get(this.channels * (x + y * this.height) + 1);
	}

	/**
	 *
	 * @return The byte for the image
	 */
	public ByteBuffer getImageBuffer() {
		return this.imageBuffer;
	}

	/**
	 * Gets the value of the red channel
	 *
	 * @param x x-coord of the pixel
	 * @param y y-coord of the pixel
	 * @return The value of the red channel
	 */
	public byte getR(final int x, final int y) {
		return this.imageBuffer.get(this.channels * (x + y * this.height) + 0);
	}
}