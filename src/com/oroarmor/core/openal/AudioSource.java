package com.oroarmor.core.openal;

import static org.lwjgl.openal.AL10.AL_BUFFER;
import static org.lwjgl.openal.AL10.AL_GAIN;
import static org.lwjgl.openal.AL10.AL_PITCH;
import static org.lwjgl.openal.AL10.alDeleteSources;
import static org.lwjgl.openal.AL10.alGenSources;
import static org.lwjgl.openal.AL10.alSource3f;
import static org.lwjgl.openal.AL10.alSourcePlay;
import static org.lwjgl.openal.AL10.alSourcef;
import static org.lwjgl.openal.AL10.alSourcei;

import org.joml.Vector3f;

import com.oroarmor.core.Destructable;
import com.oroarmor.core.Destructor;

public class AudioSource implements Destructable {

	private Vector3f position = new Vector3f(), velocity = new Vector3f();
	private float gain = 1, pitch = 1;

	private int sourceID;

	public AudioSource() {
		sourceID = alGenSources();
	}

	@Override
	public void destroy() {
		alDeleteSources(sourceID);
	}

	public float getGain() {
		return gain;
	}

	public float getPitch() {
		return pitch;
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getVelocity() {
		return velocity;
	}

	public void playSound(int soundID) {
		alSourcei(sourceID, AL_BUFFER, soundID);
		alSourcef(sourceID, AL_PITCH, pitch);
		alSourcef(sourceID, AL_GAIN, gain);
		alSource3f(sourceID, AL_PITCH, position.x, position.y, position.z);
		alSource3f(sourceID, AL_PITCH, velocity.x, velocity.y, velocity.z);
		alSourcePlay(sourceID);
		Destructor.addDestructable(this);
	}

	public void setGain(float gain) {
		this.gain = gain;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void setVelocity(Vector3f velocity) {
		this.velocity = velocity;
	}
}
