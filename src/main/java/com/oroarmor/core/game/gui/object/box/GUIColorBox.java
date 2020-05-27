package com.oroarmor.core.game.gui.object.box;

import org.joml.Vector4f;

import com.oroarmor.core.game.gui.GUICallback;
import com.oroarmor.core.game.gui.animation.IAnimation;
import com.oroarmor.core.game.gui.object.GUIObject;
import com.oroarmor.core.game.gui.shader.GUIShaders;
import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.Renderer;
import com.oroarmor.core.opengl.VertexBufferLayout;

public class GUIColorBox extends GUIObject<GUIColorBox> {

	protected float width;
	protected float height;

	protected Mesh boxMesh;

	protected Vector4f color;

	private final Vector4f originalColor = new Vector4f().zero();

	public GUIColorBox(final float x, final float y, final float width, final float height, final Vector4f color) {
		super(x, y);
		this.width = width;
		this.height = height;

		this.boxMesh = new Mesh(
				new float[] { -width / 2, -height / 2, 0, 0, width / 2, -height / 2, 1, 0, width / 2, height / 2, 1, 1,
						-width / 2, height / 2, 0, 1 },
				new int[] { 0, 1, 2, 2, 3, 0 }, new VertexBufferLayout().pushFloats(2).pushFloats(2));

		this.animationMatrix.translation(x + width / 2, y + height / 2, 0);

		this.callback = new GUICallback() {
		};

		this.color = color;

		color.add(new Vector4f().zero(), this.originalColor);

	}

	public Vector4f getOriginalColor() {
		return this.originalColor;
	}

	@Override
	public boolean inBounds(final float x, final float y) {
		return this.x < x && this.x + this.width > x && this.y < y && this.y + this.height > y;
	}

	@Override
	public void render(final Renderer renderer) {

		for (int i = 0; i < this.animations.size(); i++) {
			final long start = this.animationDurations.get(i);
			final IAnimation<GUIColorBox> animation = this.animations.get(i);

			final long duration = System.currentTimeMillis() - start;

			if (animation.getDurationInMillis() < duration) {
				this.animationDurations.remove(i);
				this.animations.remove(i);
				i--;
				continue;
			}

			final float percent = (float) duration / (float) animation.getDurationInMillis();

			animation.animate(this, percent);
		}

		this.boxMesh.render(renderer, GUIShaders.getSolidColorShader(this.color).setObjectModel(this.animationMatrix));
	}

	public void setColor(final Vector4f newColor) {
		this.color = newColor;
	}

	public void setCurrentColorAsOriginal() {
		this.color.add(new Vector4f().zero(), this.originalColor);
	}
}
