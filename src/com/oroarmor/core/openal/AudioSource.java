package com.oroarmor.core.openal;

import static org.lwjgl.openal.AL11.*;

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

	public void playSound(int soundID) {
		alSourcei(sourceID, AL_BUFFER, soundID);
		alSourcef(sourceID, AL_PITCH, pitch);
		alSourcef(sourceID, AL_GAIN, gain);
		alSource3f(sourceID, AL_PITCH, position.x, position.y, position.z);
		alSource3f(sourceID, AL_PITCH, velocity.x, velocity.y, velocity.z);
		alSourcePlay(sourceID);
		Destructor.addDestructable(this);
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3f velocity) {
		this.velocity = velocity;
	}

	public float getGain() {
		return gain;
	}

	public void setGain(float gain) {
		this.gain = gain;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	@Override
	public void destroy() {
		alDeleteSources(sourceID);
	}
}
