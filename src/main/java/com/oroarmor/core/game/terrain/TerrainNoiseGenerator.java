package com.oroarmor.core.game.terrain;

import org.joml.Random;
import org.joml.SimplexNoise;
import org.joml.Vector2f;

public class TerrainNoiseGenerator {
	public static float[][] generateNoiseMap(int width, int height, NoiseSettings settings, Vector2f positionOffset) {
		float[][] noiseMap = new float[width][height];

		Random prng = new Random(settings.getSeed());
		Vector2f[] octaveOffsets = new Vector2f[settings.getOctaves()];

		float amplitude = 1;
		float frequency = 1;
		float maxPossibleHeight = 1;
		for (int i = 0; i < settings.getOctaves(); i++) {
			float offsetX = prng.nextFloat() + settings.getOffset().x + positionOffset.x;
			float offsetY = prng.nextFloat() - settings.getOffset().y - positionOffset.y;
			octaveOffsets[i] = new Vector2f(offsetX, offsetY);

			amplitude *= settings.getPersistance();
			maxPossibleHeight += amplitude;
		}

		float halfWidth = width / 2f;
		float halfHeight = height / 2f;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				amplitude = 1;
				frequency = 1;
				float noiseHeight = 0;

				for (int i = 0; i < settings.getOctaves(); i++) {
					float sampleX = (x - halfWidth + octaveOffsets[i].x) / settings.getScale() * frequency;
					float sampleY = (y - halfHeight + octaveOffsets[i].y) / settings.getScale() * frequency;

					float perlinValue = SimplexNoise.noise(sampleX, sampleY, settings.getSeed()) * 0.5f + 0.5f;
					noiseHeight += perlinValue * amplitude;

					amplitude *= settings.getPersistance();
					frequency *= settings.getLacunarity();
				}

				noiseMap[x][y] = noiseHeight / maxPossibleHeight;

			}
		}

		return noiseMap;
	}

	public static float[][] generateNoiseMap(int width, int height, Vector2f positionOffset) {
		return generateNoiseMap(width, height, new NoiseSettings(), positionOffset);
	}

}
