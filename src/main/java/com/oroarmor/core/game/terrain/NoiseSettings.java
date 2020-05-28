package com.oroarmor.core.game.terrain;

import org.joml.Vector2f;

public class NoiseSettings {
	private float lacunarity = 2;
	private int octaves = 7;
	private Vector2f offset = new Vector2f(0, 0);

	private float persistance = .6f;

	private float scale = 1000;
	private int seed = 25;

	public NoiseSettings() {

	}

	public NoiseSettings(final float scale, final float persistance, final float lacunarity, final int octaves,
			final int seed, final Vector2f offset) {
		this.scale = scale;
		this.persistance = persistance;
		this.lacunarity = lacunarity;
		this.octaves = octaves;
		this.seed = seed;
		this.offset = offset;
	}

	public float getLacunarity() {
		return lacunarity;
	}

	public int getOctaves() {
		return octaves;
	}

	public Vector2f getOffset() {
		return offset;
	}

	public float getPersistance() {
		return persistance;
	}

	public float getScale() {
		return scale;
	}

	public int getSeed() {
		return seed;
	}
}
