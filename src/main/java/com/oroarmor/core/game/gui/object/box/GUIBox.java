package com.oroarmor.core.game.gui.object.box;

import com.oroarmor.core.game.gui.GUICallback;
import com.oroarmor.core.game.gui.animation.IAnimation;
import com.oroarmor.core.game.gui.object.GUIObject;
import com.oroarmor.core.game.gui.shader.GUIShaders;
import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.Renderer;
import com.oroarmor.core.opengl.VertexBufferLayout;
import org.joml.Vector4f;

public class GUIBox extends GUIObject<GUIBox> {
    protected float width;
    protected float height;

    protected Mesh boxMesh;

    protected Vector4f color;

    public GUIBox(float x, float y, float width, float height) {
        super(x, y);
        this.width = width;
        this.height = height;

        boxMesh = new Mesh(
                new float[]{-width / 2, -height / 2, 0, 0, width / 2, -height / 2, 1, 0, width / 2, height / 2, 1, 1,
                        -width / 2, height / 2, 0, 1},
                new int[]{0, 1, 2, 2, 3, 0}, new VertexBufferLayout().pushFloats(2).pushFloats(2));

        animationMatrix.translation(x + width / 2, y + height / 2, 0);

        color = new Vector4f(1, 1, 1, 1);

        callback = new GUICallback() {

        };

    }

    @Override
    public boolean inBounds(float x, float y) {
        return this.x < x && this.x + width > x && this.y < y && this.y + height > y;
    }

    @Override
    public void render(Renderer renderer) {

        for (int i = 0; i < animations.size(); i++) {
            long start = animationDurations.get(i);
            IAnimation<GUIBox> animation = animations.get(i);

            long duration = System.currentTimeMillis() - start;

            if (animation.getDurationInMillis() < duration) {
                animationDurations.remove(i);
                animations.remove(i);
                i--;
                continue;
            }

            float percent = (float) duration / (float) animation.getDurationInMillis();

            animation.animate(this, percent);
        }

        boxMesh.render(renderer, GUIShaders.getSolidColorShader(color).setObjectModel(animationMatrix));
    }
}
