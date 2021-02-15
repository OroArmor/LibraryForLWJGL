package com.oroarmor.core.game.gui.object.box;

import com.oroarmor.core.game.gui.GUICallback;
import com.oroarmor.core.game.gui.animation.IAnimation;
import com.oroarmor.core.game.gui.object.GUIObject;
import com.oroarmor.core.game.gui.shader.GUIShaders;
import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.Renderer;
import com.oroarmor.core.opengl.VertexBufferLayout;
import org.joml.Vector4f;

public class GUIColorBox extends GUIObject<GUIColorBox> {

    private final Vector4f originalColor = new Vector4f().zero();
    protected float width;
    protected float height;
    protected Mesh boxMesh;
    protected Vector4f color;

    public GUIColorBox(final float x, final float y, final float width, final float height, final Vector4f color) {
        super(x, y);
        this.width = width;
        this.height = height;

        boxMesh = new Mesh(
                new float[]{-width / 2, -height / 2, 0, 0, width / 2, -height / 2, 1, 0, width / 2, height / 2, 1, 1,
                        -width / 2, height / 2, 0, 1},
                new int[]{0, 1, 2, 2, 3, 0}, new VertexBufferLayout().pushFloats(2).pushFloats(2));

        animationMatrix.translation(x + width / 2, y + height / 2, 0);

        callback = new GUICallback() {
        };

        this.color = color;

        color.add(new Vector4f().zero(), originalColor);

    }

    public Vector4f getOriginalColor() {
        return originalColor;
    }

    @Override
    public boolean inBounds(final float x, final float y) {
        return this.x < x && this.x + width > x && this.y < y && this.y + height > y;
    }

    @Override
    public void render(final Renderer renderer) {

        for (int i = 0; i < animations.size(); i++) {
            final long start = animationDurations.get(i);
            final IAnimation<GUIColorBox> animation = animations.get(i);

            final long duration = System.currentTimeMillis() - start;

            if (animation.getDurationInMillis() < duration) {
                animationDurations.remove(i);
                animations.remove(i);
                i--;
                continue;
            }

            final float percent = (float) duration / (float) animation.getDurationInMillis();

            animation.animate(this, percent);
        }

        boxMesh.render(renderer, GUIShaders.getSolidColorShader(color).setObjectModel(animationMatrix));
    }

    public void setColor(final Vector4f newColor) {
        color = newColor;
    }

    public void setCurrentColorAsOriginal() {
        color.add(new Vector4f().zero(), originalColor);
    }
}
