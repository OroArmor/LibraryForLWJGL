package com.oroarmor.core.game.gui.shader.solidcolor;

import com.oroarmor.core.opengl.Shader;
import com.oroarmor.util.ResourceLoader;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class SolidColorShader extends Shader {
    public SolidColorShader() {
        super(//
                ResourceLoader.loadFileString(
                        SolidColorShader.class.getClassLoader().getResourceAsStream("com/oroarmor/core/game/gui/shader/solidcolor/solidcolor.vs")),
                ResourceLoader.loadFileString(SolidColorShader.class.getClassLoader()
                        .getResourceAsStream("com/oroarmor/core/game/gui/shader/solidcolor/solidcolor.fs")));

        compile();
    }

    public void setColor(final Vector4f color) {
        this.setUniform4f("u_color", color);
    }

    public Shader setObjectModel(final Matrix4f objectModel) {
        setUniformMat4f("u_M", objectModel);
        return this;
    }

    public void setOrthoView(final Matrix4f orthoView) {
        setUniformMat4f("u_V", orthoView);
    }

    public Shader setZ(final int i) {
        setUniform1f("u_Z", i);
        return this;
    }
}
