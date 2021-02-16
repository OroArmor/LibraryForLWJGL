package com.oroarmor.core.game.gui.animation;

import com.oroarmor.core.game.gui.object.box.GUIColorBox;
import org.joml.Vector4f;

public class ColorTransition extends Animation<GUIColorBox> {
    Vector4f color;

    public ColorTransition(long duration, Easing easing, Vector4f color) {
        super(duration, easing);
        this.color = color;
    }

    public ColorTransition(long duration, Vector4f color) {
        super(duration);
        this.color = color;
    }

    @Override
    public void animate(GUIColorBox object, float percent) {
        object.setColor(object.getOriginalColor().lerp(color, easing.calculate(percent), new Vector4f()));
    }
}
