package com.oroarmor.core.openal;

import com.oroarmor.core.Destructor;
import org.joml.Vector3f;

import static org.lwjgl.openal.AL10.*;

/**
 * A class that can play sounds
 *
 * @author OroArmor
 */
public class AudioSource {

    /**
     * The OpenAL id for this source
     */
    private final int sourceID;
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
     * The velocity of the sound
     */
    private Vector3f velocity = new Vector3f();

    /**
     * Construct an audiosource
     */
    public AudioSource() {
        sourceID = alGenSources();
        Destructor.addDestructable(this::destroy);
    }

    public void destroy() {
        alDeleteSources(sourceID);
    }

    /**
     * @return The gain of the sound
     */
    public float getGain() {
        return gain;
    }

    /**
     * Sets the gain of the source
     *
     * @param gain
     */
    public void setGain(final float gain) {
        this.gain = gain;
        alSourcef(sourceID, AL_GAIN, gain);
    }

    /**
     * @return The pitch of the sound
     */
    public float getPitch() {
        return pitch;
    }

    /**
     * Sets the pitch of the source
     *
     * @param pitch
     */
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }

    /**
     * @return The position of the sound
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * Sets the position of the source
     *
     * @param position
     */
    public void setPosition(final Vector3f position) {
        this.position = position;
    }

    /**
     * @return The velocity of the sound
     */
    public Vector3f getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity of the source
     *
     * @param velocity
     */
    public void setVelocity(final Vector3f velocity) {
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

    /**
     * Plays a sound with the set {@code gain}, {@code pitch}, {@code position}, and
     * {@code velocity}
     *
     * @param soundID The id of the sound to play
     */
    public void playSound(final int soundID) {
        alSourcei(sourceID, AL_BUFFER, soundID);
        alSourcef(sourceID, AL_PITCH, pitch);
        alSourcef(sourceID, AL_GAIN, gain);
        alSource3f(sourceID, AL_PITCH, position.x, position.y, position.z);
        alSource3f(sourceID, AL_PITCH, velocity.x, velocity.y, velocity.z);
        alSourcePlay(sourceID);
    }

    /**
     * Stops the current sound
     */
    public void stop() {
        alSourceStop(sourceID);
    }
}
