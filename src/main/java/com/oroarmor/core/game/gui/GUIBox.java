package com.oroarmor.core.game.gui;

import org.joml.Vector4f;

import com.oroarmor.core.game.gui.shader.GUIShaders;
import com.oroarmor.core.glfw.event.mouse.button.MouseButton;
import com.oroarmor.core.opengl.Mesh;
import com.oroarmor.core.opengl.Renderer;
import com.oroarmor.core.opengl.VertexBufferLayout;

public class GUIBox extends GUI {

	protected float width;
	protected float height;

	protected Mesh boxMesh;

	private Vector4f color;

	public GUIBox(float x, float y, float width, float height) {
		super(x, y);
		this.width = width;
		this.height = height;

		this.boxMesh = new Mesh(
				new float[] { x, y, 0, 0, x + width, y, 1, 0, x + width, y + width, 1, 1, x, y + width, 0, 1 },
				new int[] { 0, 1, 2, 2, 3, 0 }, new VertexBufferLayout().pushFloats(2).pushFloats(2));

		color = new Vector4f(1, 0, 0, 1);

		this.callback = new GUICallback() {
			@Override
			public void onHover() {
				color = new Vector4f(1, 0.2f, 0.2f, 1);
			}

			@Override
			public void onHoverStop() {
				color = new Vector4f(1, 0, 0, 1);
			}

			@Override
			public void onClick(MouseButton button) {
				color = new Vector4f(0, 1, 0, 1);
			}

			@Override
			public void onRelease(MouseButton button, boolean inbounds) {
				System.out.println(inbounds ? "Release In Bounds!" : "Release Out of Bounds!");
			}
		};

	}

	@Override
	public void render(Renderer renderer) {
		boxMesh.render(renderer, GUIShaders.getSolidColorShader(color).setObjectModel(animationMatrix));
	}

	@Override
	public boolean inBounds(float x, float y) {
		return this.x < x && this.x + this.width > x && this.y < y && this.y + this.height > y;
	}
}
