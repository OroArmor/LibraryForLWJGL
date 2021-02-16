package com.oroarmor.core.game.terrain;

import com.oroarmor.core.game.light.Pointlight;
import com.oroarmor.core.game.light.Sunlight;
import com.oroarmor.core.opengl.Shader;
import com.oroarmor.util.ResourceLoader;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class TerrainShader extends Shader {

    public static int MAX_POINT_LIGHTS = 10;
    public static int MAX_SUN_LIGHTS = 3;
    private final Pointlight[] pointlights = new Pointlight[MAX_POINT_LIGHTS];
    private final Sunlight[] sunlights = new Sunlight[MAX_SUN_LIGHTS];
    private int currentPointlight = 0;
    private int currentSunlight = 0;

    public TerrainShader() {
        super(ResourceLoader.loadFileString(TerrainShader.class.getClassLoader().getResourceAsStream("com/oroarmor/core/game/terrain/terrainvs.vs")),
                ResourceLoader.loadFileString(TerrainShader.class.getClassLoader().getResourceAsStream("com/oroarmor/core/game/terrain/terrainfs.fs")));
        compile();
    }

    public void addPointlight(Pointlight light) {

        if (currentPointlight == MAX_SUN_LIGHTS) {
            System.out.println("Max sunlights reached");
            return;
        }

        pointlights[currentPointlight++] = light;
    }

    public void addSunlight(Sunlight light) {
        if (currentSunlight == MAX_SUN_LIGHTS) {
            System.out.println("Max sunlights reached");
            return;
        }

        sunlights[currentSunlight++] = light;
    }

    public void update() {
        bind();

        setUniform1f("u_waterHeight", .002f * TerrainMesh.maxHeight);
        setUniform1f("u_waterWarmHeight", .004f * TerrainMesh.maxHeight);
        setUniform1f("u_sandHeight", .05f * TerrainMesh.maxHeight);
        setUniform1f("u_grassHeight", .5f * TerrainMesh.maxHeight);
        setUniform1f("u_rockHeight", .8f * TerrainMesh.maxHeight);
        setUniform1f("u_snowHeight", 1f * TerrainMesh.maxHeight);

        for (int i = 0; i < MAX_SUN_LIGHTS; i++) {
            Sunlight sunlight = sunlights[i];

            if (sunlight == null) {
                setUniform3f("u_sunlights[" + i + "].direction", new Vector3f(0, 1, 0));
                this.setUniform4f("u_sunlights[" + i + "].color", new Vector4f(0, 0, 0, 1));
            } else {
                setUniform3f("u_sunlights[" + i + "].direction", sunlight.getDirection());
                this.setUniform4f("u_sunlights[" + i + "].color", sunlight.getColor());
            }
        }

        for (int i = 0; i < MAX_POINT_LIGHTS; i++) {
            Pointlight pointlight = pointlights[i];

            if (pointlight == null) {
                setUniform3f("u_pointlights[" + i + "].position", new Vector3f(0, 0, 0));
                this.setUniform4f("u_pointlights[" + i + "].color", new Vector4f(0, 0, 0, 1));
                setUniform1f("u_pointlights[" + i + "].strength", 0);
            } else {
                setUniform3f("u_pointlights[" + i + "].position", pointlight.getPosition());
                this.setUniform4f("u_pointlights[" + i + "].color", pointlight.getColor());
                setUniform1f("u_pointlights[" + i + "].strength", pointlight.getStrength());
            }
        }
    }
}
