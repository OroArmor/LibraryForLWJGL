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
import static org.lwjgl.openal.AL10.*;

import org.joml.Vector3f;

import com.oroarmor.core.Destructable;
import com.oroarmor.core.Destructor;

/**
 * A class that can play sounds
 * 
 * @author OroArmor
 *
 */
public class AudioSource implements Destructable {

	/**
	 * Gain of the sound
	 */
	private float gain = 1;

	/**
	 * The pitch of the sound
	 */
	private float pitch = 1;

	/**
	 * The position of the sound
	 */
	private Vector3f position = new Vector3f();

	/**
	 * The OpenAL id for this source
	 */
	private int sourceID;

	/**
	 * The velocity of the sound
	 */
	private Vector3f velocity = new Vector3f();

	/**
	 * Construct an audiosource
	 */
	public AudioSource() {
		sourceID = alGenSources();
		Destructor.addDestructable(this);
	}

	@Override
	public void destroy() {
		alDeleteSources(sourceID);
	}

	/**
	 * 
	 * @return The gain of the sound
	 */
	public float getGain() {
		return gain;
	}

	/**
	 * 
	 * @return The pitch of the sound
	 */
	public float getPitch() {
		return pitch;
	}

	/**
	 * 
	 * @return The position of the sound
	 */
	public Vector3f getPosition() {
		return position;
	}

	/**
	 * 
	 * @return The velocity of the sound
	 */
	public Vector3f getVelocity() {
		return velocity;
	}

	/**
	 * Plays a sound with the set {@code gain}, {@code pitch}, {@code position}, and
	 * {@code velocity}
	 * 
	 * @param soundID The id of the sound to play
	 */
	public void playSound(int soundID) {
		alSourcei(sourceID, AL_BUFFER, soundID);
		alSourcef(sourceID, AL_PITCH, pitch);
		alSourcef(sourceID, AL_GAIN, gain);
		alSource3f(sourceID, AL_PITCH, position.x, position.y, position.z);
		alSource3f(sourceID, AL_PITCH, velocity.x, velocity.y, velocity.z);
		alSourcePlay(sourceID);
	}

	/**
	 * Sets the gain of the source
	 * 
	 * @param gain
	 */
	public void setGain(float gain) {
		this.gain = gain;
		alSourcef(sourceID, AL_GAIN, gain);
	}

	/**
	 * Sets the pitch of the source
	 * 
	 * @param pitch
	 */
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	/**
	 * Sets the position of the source
	 * 
	 * @param position
	 */
	public void setPosition(Vector3f position) {
		this.position = position;
	}

	/**
	 * Sets the velocity of the source
	 * 
	 * @param velocity
	 */
	public void setVelocity(Vector3f velocity) {
		this.velocity = velocity;
	}

	/**
	 * Returns if the current source is not playing or has finished playing a sound
	 * 
	 * @return True if sound is not playing, false if sound is playing
	 */
	public boolean isFinished() {
		return AL_PLAYING != alGetSourcei(sourceID, AL_SOURCE_STATE);
	}
}
