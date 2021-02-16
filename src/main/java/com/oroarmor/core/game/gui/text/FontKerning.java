package com.oroarmor.core.game.gui.text;

import java.util.HashMap;
import java.util.Map;

public class FontKerning {
    Map<FontCharacter, HashMap<FontCharacter, Integer>> kernings;

    public FontKerning() {
        kernings = new HashMap<>();
    }

    public void addKerning(FontCharacter leading, FontCharacter following, int offset) {
        if (kernings.containsKey(leading)) {
            kernings.get(leading).put(following, offset);
        } else {
            HashMap<FontCharacter, Integer> map = new HashMap<>();
            map.put(following, offset);
            kernings.put(following, map);
        }
    }

    public int getKerning(FontCharacter leading, FontCharacter following) {
        if (kernings.containsKey(leading)) {
            if (kernings.get(leading).containsKey(following)) {
                return kernings.get(leading).get(following);
            }
        }
        return 0;
    }
}
