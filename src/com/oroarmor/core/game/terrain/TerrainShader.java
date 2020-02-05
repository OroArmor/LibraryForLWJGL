package com.oroarmor.core.game.terrain;

import com.oroarmor.core.opengl.Shader;
import com.oroarmor.util.ResourceLoader;

public class TerrainShader extends Shader {

	public TerrainShader() {
		super(ResourceLoader.loadFile(Class.class.getResourceAsStream("/com/oroarmor/core/game/terrain/terrainvs.vs")),
				ResourceLoader
						.loadFile(Class.class.getResourceAsStream("/com/oroarmor/core/game/terrain/terrainfs.fs")));
	}

	public void update() {
		this.bind();

		this.setUniform1f("u_waterHeight", 14);
		this.setUniform1f("u_sandHeight", 15);
		this.setUniform1f("u_grassHeight", 20);
		this.setUniform1f("u_rockHeight", 27);
		this.setUniform1f("u_snowHeight", 30);
	}

}
