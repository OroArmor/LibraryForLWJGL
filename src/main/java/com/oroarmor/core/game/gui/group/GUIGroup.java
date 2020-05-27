package com.oroarmor.core.game.gui.group;

import java.util.ArrayList;
import java.util.List;

import com.oroarmor.core.game.gui.IGUI;
import com.oroarmor.core.game.gui.animation.IAnimation;
import com.oroarmor.core.game.gui.object.IGUIObject;
import com.oroarmor.core.opengl.Renderer;

public abstract class GUIGroup implements IGUIGroup {

	protected float x;
	protected float y;
	protected boolean hidden;
	protected List<IGUI<?>> children;

	protected boolean hasParent = false;

	public GUIGroup(final float x, final float y) {
		this(x, y, false);
	}

	public GUIGroup(final float x, final float y, final boolean hiddenOnCreation) {
		this.x = x;
		this.y = y;
		this.hidden = hiddenOnCreation;
	}

	@Override
	public void addChildren(final IGUI<?>... newChildren) {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}

		for (final IGUI<?> newChild : newChildren) {
			if (newChild.hasParent()) {
				continue;
			}

			if (newChild instanceof IGUIGroup) {
				final IGUIGroup newGUIGroup = (IGUIGroup) newChild;

				if (newGUIGroup.getChildren().contains(this)) {
					continue;
				}
			}
			this.children.add(newChild);
		}
	}

	@Override
	public List<IGUI<?>> getChildren() {
		return this.children;
	}

	@Override
	public float getX() {
		return this.x;
	}

	@Override
	public float getY() {
		return this.y;
	}

	@Override
	public boolean hasParent() {
		return this.hasParent;
	}

	@Override
	public void hideAll() {
		this.hidden = true;
	}

	@Override
	public boolean isVisable() {
		return this.hidden;
	}

	@Override
	public void makeVisable(final boolean visable) {
		this.hidden = !visable;

		for (final IGUI<?> igui : this.children) {
			if (igui instanceof IGUIObject) {
				((IGUIObject<?>) igui).setActive(visable);
			}

			if (igui instanceof IGUIGroup) {
				((IGUIGroup) igui).makeVisable(visable);
			}
		}
	}

	@Override
	public int numObjects() {
		return this.children == null ? 0 : this.children.size();
	}

	@Override
	public void render(final Renderer renderer) {

	}

	@Override
	public void renderChildren(final Renderer renderer) {
		if (this.children == null || this.hidden) {
			return;
		}

		for (final IGUI<?> child : this.children) {
			child.render(renderer);

			if (child instanceof IGUIGroup) {
				((IGUIGroup) child).renderChildren(renderer);
			}
		}
	}

	@Override
	public void setHasParent(final boolean hasParent) {
		this.hasParent = hasParent;
	}

	@Override
	public void showAll() {
		this.hidden = false;
	}

	@Override
	public void triggerAnimation(final IAnimation<IGUIGroup> animation) {
	}

}
