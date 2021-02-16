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

    public GUIGroup(float x, float y) {
        this(x, y, false);
    }

    public GUIGroup(float x, float y, boolean hiddenOnCreation) {
        this.x = x;
        this.y = y;
        hidden = hiddenOnCreation;
    }

    @Override
    public void addChildren(IGUI<?>... newChildren) {
        if (children == null) {
            children = new ArrayList<>();
        }

        for (IGUI<?> newChild : newChildren) {
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
    public boolean isVisible() {
        return hidden;
    }

    @Override
    public void makeVisible(boolean visible) {
        hidden = !visible;

        for (IGUI<?> igui : children) {
            if (igui instanceof IGUIObject) {
                ((IGUIObject<?>) igui).setActive(visible);
            }

            if (igui instanceof IGUIGroup) {
                ((IGUIGroup) igui).makeVisible(visible);
            }
        }
    }

    @Override
    public int numObjects() {
        return children == null ? 0 : children.size();
    }

    @Override
    public void render(Renderer renderer) {
    }

    @Override
    public void renderChildren(Renderer renderer) {
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
    public void setHasParent(boolean hasParent) {
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
