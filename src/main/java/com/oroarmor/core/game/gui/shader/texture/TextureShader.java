package com.oroarmor.core.game.gui.shader.texture;

import com.oroarmor.core.opengl.Shader;
import com.oroarmor.core.opengl.Texture;
import com.oroarmor.util.ResourceLoader;
import org.joml.Matrix4f;

public class TextureShader extends Shader {
    public TextureShader() {
        super(//
                ResourceLoader.loadFileString(
                        TextureShader.class.getClassLoader().getResourceAsStream("com/oroarmor/core/game/gui/shader/texture/texture.vs")),
                ResourceLoader.loadFileString(
                        TextureShader.class.getClassLoader().getResourceAsStream("com/oroarmor/core/game/gui/shader/texture/texture.fs")));

        compile();
    }

    public TextureShader setObjectModel(final Matrix4f objectModel) {
        setUniformMat4f("u_M", objectModel);
        return this;
    }

    public void setOrthoView(final Matrix4f orthoView) {
        setUniformMat4f("u_V", orthoView);
    }

    public TextureShader setTexture(final Texture texture) {
        texture.bind(0);
        setUniform1i("u_Texture", 0);
        return this;
    }

    public TextureShader setZ(final int i) {
        setUniform1f("u_Z", i);
        return this;
    }
}
