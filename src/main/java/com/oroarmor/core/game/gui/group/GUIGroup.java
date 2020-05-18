package com.oroarmor.core.game.gui.group;

import java.util.ArrayList;
import java.util.List;

import com.oroarmor.core.game.gui.IGUI;
import com.oroarmor.core.game.gui.animation.IAnimation;
import com.oroarmor.core.game.gui.object.IGUIObject;
import com.oroarmor.core.opengl.Renderer;

public abstract class GUIGroup implements IGUIGroup<GUIGroup> {

	protected float x;
	protected float y;
	protected boolean hidden;
	protected List<IGUI<?>> children;

	protected boolean hasParent = false;

	public GUIGroup(float x, float y) {
		this(x, y, false);
	}

	public GUIGroup(float x, float y, boolean hiddenOnCreation) {
		this.x = x;
		this.y = y;
		this.hidden = hiddenOnCreation;
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
	public int numObjects() {
		return (children == null) ? 0 : children.size();
	}

	@Override
	public List<IGUI<?>> getChildren() {
		return children;
	}

	@Override
	public void renderChildren(Renderer renderer) {
		if (children == null || hidden) {
			return;
		}

		for (IGUI<?> child : children) {
			child.render(renderer);

			if (child instanceof IGUIGroup) {
				((IGUIGroup<?>) child).renderChildren(renderer);
			}
		}
	}

	@Override
	public void hideAll() {
		this.hidden = true;
	}

	@Override
	public void showAll() {
		this.hidden = false;
	}

	@Override
	public boolean isVisable() {
		return hidden;
	}

	@Override
	public void addChildren(IGUI<?>... newChildren) {
		if (children == null) {
			children = new ArrayList<IGUI<?>>();
		}

		for (IGUI<?> newChild : newChildren) {
			if (newChild.hasParent()) {
				continue;
			}

			if (newChild instanceof IGUIGroup) {
				IGUIGroup<?> newGUIGroup = (IGUIGroup<?>) newChild;

				if (newGUIGroup.getChildren().contains(this)) {
					continue;
				}
			}
			this.children.add(newChild);
		}
	}

	@Override
	public boolean hasParent() {
		return hasParent;
	}

	@Override
	public void setHasParent(boolean hasParent) {
		this.hasParent = hasParent;
	}

	@Override
	public void render(Renderer renderer) {

	}

	@Override
	public void makeVisable(boolean visable) {
		this.hidden = !visable;

		for (IGUI<?> igui : children) {
			if (igui instanceof IGUIObject) {
				((IGUIObject<?>) igui).setActive(visable);
			}

			if (igui instanceof IGUIGroup) {
				((IGUIGroup<?>) igui).makeVisable(visable);
			}
		}
	}

	@Override
	public void triggerAnimation(IAnimation<IGUIGroup<GUIGroup>> animation) {

	}

}
