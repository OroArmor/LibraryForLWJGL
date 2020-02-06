package com.oroarmor.core.game.terrain;

import org.joml.Vector3f;
import org.joml.Vector4f;

import com.oroarmor.core.game.light.Pointlight;
import com.oroarmor.core.game.light.Sunlight;
import com.oroarmor.core.opengl.Shader;
import com.oroarmor.util.ResourceLoader;

public class TerrainShader extends Shader {

	public static int MAX_SUN_LIGHTS = 3;
	public static int MAX_POINT_LIGHTS = 10;

	private Sunlight[] sunlights = new Sunlight[MAX_SUN_LIGHTS];
	private int currentSunlight = 0;

	private Pointlight[] pointlights = new Pointlight[MAX_POINT_LIGHTS];
	private int currentPointlight = 0;

	public TerrainShader() {
		super(ResourceLoader.loadFile(Class.class.getResourceAsStream("/com/oroarmor/core/game/terrain/terrainvs.vs")),
				ResourceLoader
						.loadFile(Class.class.getResourceAsStream("/com/oroarmor/core/game/terrain/terrainfs.fs")));

		this.compile();

	}

	public void update() {
		this.bind();

		this.setUniform1f("u_waterHeight", .02f * TerrainMesh.maxHeight);
		this.setUniform1f("u_waterWarmHeight", .04f * TerrainMesh.maxHeight);
		this.setUniform1f("u_sandHeight", .1f * TerrainMesh.maxHeight);
		this.setUniform1f("u_grassHeight", .5f * TerrainMesh.maxHeight);
		this.setUniform1f("u_rockHeight", .8f * TerrainMesh.maxHeight);
		this.setUniform1f("u_snowHeight", 1f * TerrainMesh.maxHeight);

		for (int i = 0; i < MAX_SUN_LIGHTS; i++) {
			Sunlight sunlight = sunlights[i];

			if (sunlight == null) {
				this.setUniform3f("u_sunlights[" + i + "].direction", new Vector3f(0, 1, 0));
				this.setUniform4f("u_sunlights[" + i + "].color", new Vector4f(0, 0, 0, 1));
			} else {
				this.setUniform3f("u_sunlights[" + i + "].direction", sunlight.getDirection());
				this.setUniform4f("u_sunlights[" + i + "].color", sunlight.getColor());
			}
		}

		for (int i = 0; i < MAX_POINT_LIGHTS; i++) {
			Pointlight pointlight = pointlights[i];

			if (pointlight == null) {
				this.setUniform3f("u_pointlights[" + i + "].position", new Vector3f(0, 0, 0));
				this.setUniform4f("u_pointlights[" + i + "].color", new Vector4f(0, 0, 0, 1));
				this.setUniform1f("u_pointlights[" + i + "].strength", 0);
			} else {
				this.setUniform3f("u_pointlights[" + i + "].position", pointlight.getPosition());
				this.setUniform4f("u_pointlights[" + i + "].color", pointlight.getColor());
				this.setUniform1f("u_pointlights[" + i + "].strength", pointlight.getStrength());
			}
		}
	}

	public void addSunlight(Sunlight light) {

		if (currentSunlight == MAX_SUN_LIGHTS) {
			System.out.println("Max sunlights reached");
			return;
		}

		sunlights[currentSunlight++] = light;
	}

	public void addPointlight(Pointlight light) {

		if (currentPointlight == MAX_SUN_LIGHTS) {
			System.out.println("Max sunlights reached");
			return;
		}

		pointlights[currentPointlight++] = light;
	}

}
