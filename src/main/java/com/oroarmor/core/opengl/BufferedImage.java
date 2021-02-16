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

    public BufferedImage(String filePath) {

        int[] x = new int[1];
        int[] y = new int[1];
        int[] channels_in_file = new int[1];
        int desired_channels = 3;

        imageBuffer = STBImage.stbi_load(filePath, x, y, channels_in_file, desired_channels);

        System.out.println(channels_in_file[0]);

        channels = channels_in_file[0];

        width = x[0];
        height = y[0];

        System.out.println(width);

        System.out.println(getR(0, 0) & 0xFF);
        System.out.println(getG(0, 0) & 0xFF);
        System.out.println(getB(0, 0) & 0xFF);
        System.out.println(getA(0, 0) & 0xFF);
    }

    /**
     * Gets the value of the alpha channel
     *
     * @param x x-coord of the pixel
     * @param y y-coord of the pixel
     * @return The value of the alpha channel
     */
    public byte getA(int x, int y) {
        if (channels < 4) {
            return 0;
        }

        return imageBuffer.get(channels * (x + y * height) + 3);
    }

    /**
     * Gets the value of the blue channel
     *
     * @param x x-coord of the pixel
     * @param y y-coord of the pixel
     * @return The value of the blue channel
     */
    public byte getB(int x, int y) {
        return imageBuffer.get(channels * (x + y * height) + 2);
    }

    /**
     * Gets the value of the green channel
     *
     * @param x x-coord of the pixel
     * @param y y-coord of the pixel
     * @return The value of the green channel
     */
    public byte getG(int x, int y) {
        return imageBuffer.get(channels * (x + y * height) + 1);
    }

    /**
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
        return imageBuffer.get(channels * (x + y * height));
    }
}