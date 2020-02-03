package com.oroarmor.core.openal;

import static org.lwjgl.openal.AL11.*;
import static org.lwjgl.openal.ALC11.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.system.libc.LibCStdlib.*;
import static org.lwjgl.system.MemoryStack.*;

import java.nio.*;
import java.util.HashMap;
import java.util.function.Consumer;

import org.lwjgl.openal.*;

import com.oroarmor.core.Destructable;
import com.oroarmor.core.Destructor;

@SuppressWarnings("unused")
public class AudioMaster implements Destructable {
	private static long context;
	private static long device;

	private static AudioMaster instance = new AudioMaster();

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

	private static HashMap<String, Integer> sounds;

	public static int loadSound(String path, String name) {
		int soundIDBuffer = AL11.alGenBuffers();

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

	public static int getSound(String soundName) {
		return sounds.get(soundName);
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
