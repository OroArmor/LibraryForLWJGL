package com.oroarmor.core.game.terrain;

import org.joml.Vector2f;

public class NoiseSettings {
	private float scale = 50;
	private float persistance = .6f;
	private float lacunarity = 2;

	private int octaves = 6;

	private int seed = 0;
	private Vector2f offset = new Vector2f(0, 0);

	public NoiseSettings() {

	}

	public NoiseSettings(float scale, float persistance, float lacunarity, int octaves, int seed, Vector2f offset) {
		this.scale = scale;
		this.persistance = persistance;
		this.lacunarity = lacunarity;
		this.octaves = octaves;
		this.seed = seed;
		this.offset = offset;
	}

	public float getScale() {
		return scale;
	}

	public float getPersistance() {
		return persistance;
	}

	public float getLacunarity() {
		return lacunarity;
	}

	public int getOctaves() {
		return octaves;
	}

	public int getSeed() {
		return seed;
	}

	public Vector2f getOffset() {
		return offset;
	}
}
