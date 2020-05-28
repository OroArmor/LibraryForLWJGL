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
		hidden = hiddenOnCreation;
	}

	@Override
	public void addChildren(final IGUI<?>... newChildren) {
		if (children == null) {
			children = new ArrayList<>();
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
			children.add(newChild);
		}
	}

	@Override
	public List<IGUI<?>> getChildren() {
		return children;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public boolean hasParent() {
		return hasParent;
	}

	@Override
	public void hideAll() {
		hidden = true;
	}

	@Override
	public boolean isVisable() {
		return hidden;
	}

	@Override
	public void makeVisable(final boolean visable) {
		hidden = !visable;

		for (final IGUI<?> igui : children) {
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
		return children == null ? 0 : children.size();
	}

	@Override
	public void render(final Renderer renderer) {

	}

	@Override
	public void renderChildren(final Renderer renderer) {
		if (children == null || hidden) {
			return;
		}

		for (final IGUI<?> child : children) {
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
		hidden = false;
	}

	@Override
	public void triggerAnimation(final IAnimation<IGUIGroup> animation) {
	}

}
