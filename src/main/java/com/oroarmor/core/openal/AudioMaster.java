package com.oroarmor.core.openal;

import static org.lwjgl.openal.AL10.AL_FORMAT_MONO16;
import static org.lwjgl.openal.AL10.AL_FORMAT_STEREO16;
import static org.lwjgl.openal.AL10.alBufferData;
import static org.lwjgl.openal.AL10.alDeleteBuffers;
import static org.lwjgl.openal.ALC10.ALC_DEFAULT_DEVICE_SPECIFIER;
import static org.lwjgl.openal.ALC10.alcCloseDevice;
import static org.lwjgl.openal.ALC10.alcCreateContext;
import static org.lwjgl.openal.ALC10.alcDestroyContext;
import static org.lwjgl.openal.ALC10.alcGetString;
import static org.lwjgl.openal.ALC10.alcMakeContextCurrent;
import static org.lwjgl.openal.ALC10.alcOpenDevice;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_decode_filename;
import static org.lwjgl.system.MemoryStack.stackMallocInt;
import static org.lwjgl.system.MemoryStack.stackPop;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.libc.LibCStdlib.free;

import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.HashMap;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;

import com.oroarmor.core.Destructable;
import com.oroarmor.core.Destructor;

@SuppressWarnings("unused")
/**
 * This class initializes and creates sounds for OpenAL
 * 
 * @author OroArmor
 *
 */
public class AudioMaster implements Destructable {
	/**
	 * The context for OpenAL
	 */
	private static long context;

	/**
	 * The device for OpenAL
	 */
	private static long device;

	/**
	 * An instance of AudioMaster to close OpenAL properly
	 */
	private static AudioMaster instance = new AudioMaster();

	/**
	 * A map of the sounds to their name
	 */
	private static HashMap<String, Integer> sounds;

	static {
		String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
		device = alcOpenDevice(defaultDeviceName);

		int[] attributes = { 0 };
		context = alcCreateContext(device, attributes);
		alcMakeContextCurrent(context);

		ALCCapabilities alcCapabilities = ALC.createCapabilities(device);
		ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);

		sounds = new HashMap<String, Integer>();

		Destructor.addDestructable(instance);
	}

	/**
	 * 
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

		ShortBuffer rawAudioBuffer = stb_vorbis_decode_filename(path, channelsBuffer, sampleRateBuffer);

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
		alBufferData(soundIDBuffer, format, rawAudioBuffer, sampleRate);

		// Free the memory allocated by STB
		free(rawAudioBuffer);

		sounds.put(name, soundIDBuffer);

		return soundIDBuffer;
	}

	@Override
	public void destroy() {
		Integer[] soundBufferIDs = new Integer[sounds.size()];

		sounds.values().toArray(soundBufferIDs);

		for (int soundBufferID : soundBufferIDs) {
			alDeleteBuffers(soundBufferID);
		}

		alcDestroyContext(context);
		alcCloseDevice(device);
	}
}
