package com.oroarmor.core.openal;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.HashMap;

import com.oroarmor.core.Destructor;
import com.oroarmor.util.ResourceLoader;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_decode_memory;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.libc.LibCStdlib.free;

/**
 * This class initializes and creates sounds for OpenAL
 *
 * @author OroArmor
 */
public final class AudioMaster {
    /**
     * The context for OpenAL
     */
    private static long context;

    /**
     * The device for OpenAL
     */
    private static long device;

    /**
     * A map of the sounds to their name
     */
    private static final HashMap<String, Integer> sounds = new HashMap<>();

    public static boolean initialize() {
        try {
            device = alcOpenDevice((ByteBuffer) null);
            ALCCapabilities alcCapabilities = ALC.createCapabilities(device);

            int[] attributes = {0};
            context = alcCreateContext(device, attributes);
            alcMakeContextCurrent(context);

            AL.createCapabilities(alcCapabilities);
        } catch (Exception e) {
            e.printStackTrace();
            destroy();
            return false;
        }

        Destructor.addDestructable(AudioMaster::destroy);
        return true;
    }

    /**
     * @param soundName Name of the sound to play
     * @return The OpenAL sound id
     */
    public static int getSound(String soundName) {
        return sounds.get(soundName);
    }

    /**
     * Loads a sound from the path, saving the name and id into a map
     *
     * @param path Path to the sound
     * @param name Name for the sound. NOT part of the path, just an identifier
     * @return The OpenAL sound id for the sound
     */
    public static int loadSound(String path, String name) {
        int soundIDBuffer = AL10.alGenBuffers();

        // Allocate space to store return information from the function
        stackPush();
        IntBuffer channelsBuffer = stackMallocInt(1);
        stackPush();
        IntBuffer sampleRateBuffer = stackMallocInt(1);

        byte[] bytes = ResourceLoader.loadFileBytes(AudioMaster.class.getClassLoader().getResourceAsStream(path));

        ByteBuffer data = MemoryUtil.memAlloc(bytes.length);
        data.put(bytes);
        data.position(0);

        ShortBuffer rawAudioBuffer = stb_vorbis_decode_memory(data, channelsBuffer, sampleRateBuffer);

        // Retreive the extra information that was stored in the buffers by the function
        int channels = channelsBuffer.get();
        int sampleRate = sampleRateBuffer.get();
        // Free the space we allocated earlier
        stackPop();
        stackPop();

        // Find the correct OpenAL format
        int format = -1;
        if (channels == 1) {
            format = AL_FORMAT_MONO16;
        } else if (channels == 2) {
            format = AL_FORMAT_STEREO16;
        }

        // Send the data to OpenAL
        assert rawAudioBuffer != null;
        alBufferData(soundIDBuffer, format, rawAudioBuffer, sampleRate);

        // Free the memory allocated by STB
        free(rawAudioBuffer);

        sounds.put(name, soundIDBuffer);

        return soundIDBuffer;
    }

    public static void destroy() {
        for (int soundBufferID : sounds.values()) {
            alDeleteBuffers(soundBufferID);
        }

        alcDestroyContext(context);
        alcCloseDevice(device);
    }
}
