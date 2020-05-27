package com.oroarmor.core.game.gui.object.box;

import org.joml.Vector4f;

import com.oroarmor.core.game.gui.GUICallback;
import com.oroarmor.core.game.gui.animation.IAnimation;
import com.oroarmor.core.game.gui.object.GUIObject;
import com.oroarmor.core.game.gui.shader.GUIShaders;
import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.Renderer;
import com.oroarmor.core.opengl.VertexBufferLayout;

public class GUIBox extends GUIObject<GUIBox> {

	protected float width;
	protected float height;

	protected Mesh boxMesh;

	protected Vector4f color;

	public GUIBox(final float x, final float y, final float width, final float height) {
		super(x, y);
		this.width = width;
		this.height = height;

		this.boxMesh = new Mesh(
				new float[] { -width / 2, -height / 2, 0, 0, width / 2, -height / 2, 1, 0, width / 2, height / 2, 1, 1,
						-width / 2, height / 2, 0, 1 },
				new int[] { 0, 1, 2, 2, 3, 0 }, new VertexBufferLayout().pushFloats(2).pushFloats(2));

		this.animationMatrix.translation(x + width / 2, y + height / 2, 0);

		this.color = new Vector4f(1, 1, 1, 1);

		this.callback = new GUICallback() {

		};

	}

	@Override
	public boolean inBounds(final float x, final float y) {
		return this.x < x && this.x + this.width > x && this.y < y && this.y + this.height > y;
	}

	@Override
	public void render(final Renderer renderer) {

		for (int i = 0; i < this.animations.size(); i++) {
			final long start = this.animationDurations.get(i);
			final IAnimation<GUIBox> animation = this.animations.get(i);

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
}
