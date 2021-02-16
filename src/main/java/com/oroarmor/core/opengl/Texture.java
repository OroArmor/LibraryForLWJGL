package com.oroarmor.core.opengl;

import java.nio.ByteBuffer;

import com.oroarmor.core.Bindable;
import com.oroarmor.core.Destructor;
import com.oroarmor.util.ResourceLoader;
import com.oroarmor.util.TextureLoader;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.stb.STBImage.STBI_rgb_alpha;
import static org.lwjgl.stb.STBImage.stbi_load_from_memory;

/**
 * A class that represents a texture in OpenGL
 *
 * @author OroArmor
 */
public class Texture implements Bindable {
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
     * For subclasses
     */
    protected Texture() {
    }

    /**
     * Creates a new {@link Texture} based on a path to an image
     *
     * @param filePath The path to the image texture
     */
    public Texture(String filePath) {
        this.filePath = filePath;

        STBImage.stbi_set_flip_vertically_on_load(true);

        byte[] bytes = ResourceLoader.loadFileBytes(Texture.class.getClassLoader().getResourceAsStream(filePath));

        ByteBuffer data = MemoryUtil.memAlloc(bytes.length);
        ByteBuffer buffer;
        try (var stack = MemoryStack.stackPush()) {
            data.put(bytes);
            data.position(0);
            var widthPointer = stack.mallocInt(1);
            var heightPointer = stack.mallocInt(1);
            var formatPointer = stack.mallocInt(1);
            buffer = stbi_load_from_memory(data, widthPointer, heightPointer, formatPointer, STBI_rgb_alpha);
            width = widthPointer.get(0);
            height = heightPointer.get(0);
        } finally {
            MemoryUtil.memFree(data);
        }

        if (buffer == null) {
            throw new IllegalArgumentException("Incorrect file path: " + filePath);
        }

        textureID = TextureLoader.loadTexture(buffer, width, height);
        STBImage.stbi_image_free(buffer);

        Destructor.addDestructable(this::destroy);
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
        this.bind();
    }

    public void destroy() {
        glDeleteTextures(textureID);
    }

    /**
     * @return The path to the image of the texture
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @return The height of the texture
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return The slot that the texture is bound to currently
     */
    public int getSlot() {
        return slot;
    }

    /**
     * @return The width of the image
     */
    public int getWidth() {
        return width;
    }

    @Override
    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
