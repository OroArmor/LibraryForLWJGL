package com.oroarmor.core.game.gui.shader;

import java.util.HashMap;
import java.util.Map;

import com.oroarmor.core.game.gui.shader.font.FontShader;
import com.oroarmor.core.game.gui.shader.solidcolor.SolidColorShader;
import com.oroarmor.core.game.gui.shader.texture.TextureShader;
import com.oroarmor.core.game.gui.text.Font;
import org.joml.Matrix4f;
import org.joml.Vector4f;

public class GUIShaders {

    private static SolidColorShader solidColorShader;

    private static Map<Font, FontShader> fontShaders;

    private static TextureShader textureShader;

    public static FontShader getFontShader(Font font) {
        if (fontShaders == null) {
            fontShaders = new HashMap<>(1);
        }

        FontShader shader;
        if (fontShaders.containsKey(font)) {
            shader = fontShaders.get(font);
        } else {
            shader = new FontShader();
            fontShaders.put(font, shader);
        }

        shader.bind();
        shader.setTexture(font.getTexture());
        shader.unbind();

        return shader;
    }

    public static SolidColorShader getSolidColorShader(final Vector4f color) {
        if (solidColorShader == null) {
            solidColorShader = new SolidColorShader();
        }
        solidColorShader.bind();
        solidColorShader.setColor(color);
        return solidColorShader;
    }

    public static TextureShader getTextureShader() {
        if (textureShader == null) {
            textureShader = new TextureShader();
        }

        textureShader.bind();

        return textureShader;
    }

    public static void updateShaderView(final Matrix4f orthoView) {
        if (solidColorShader != null) {
            solidColorShader.bind();
            solidColorShader.setOrthoView(orthoView);
        }

        if (fontShaders != null) {
            for (final FontShader shader : fontShaders.values()) {
                shader.bind();
                shader.setOrthoView(orthoView);
            }
        }

        if (textureShader != null) {
            textureShader.bind();
            textureShader.setOrthoView(orthoView);
        }
    }

}
