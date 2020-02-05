package com.oroarmor.core.game.terrain;

import com.oroarmor.core.opengl.Shader;
import com.oroarmor.util.ResourceLoader;

public class TerrainShader extends Shader {

	public TerrainShader() {
		super(ResourceLoader.loadFile(Class.class.getResourceAsStream("/com/oroarmor/core/game/terrain/terrainvs.vs")),
				ResourceLoader
						.loadFile(Class.class.getResourceAsStream("/com/oroarmor/core/game/terrain/terrainfs.fs")));

		this.compile();

	}

	public void update() {
		this.bind();

		float maxHeight = 20;

		this.setUniform1f("u_waterHeight", .1f * maxHeight);
		this.setUniform1f("u_waterWarmHeight", .2f * maxHeight);
		this.setUniform1f("u_sandHeight", .3f * maxHeight);
		this.setUniform1f("u_grassHeight", .6f * maxHeight);
		this.setUniform1f("u_rockHeight", .9f * maxHeight);
		this.setUniform1f("u_snowHeight", 1f * maxHeight);
	}

}
