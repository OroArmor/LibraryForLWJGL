package com.oroarmor.core.game.gui.text;

import java.util.HashMap;
import java.util.Map;

public class FontKerning {

	Map<FontCharacter, HashMap<FontCharacter, Integer>> kernings;

	public FontKerning() {
		this.kernings = new HashMap<>();
	}

	public void addKerning(final FontCharacter leading, final FontCharacter following, final int offset) {
		if (this.kernings.containsKey(leading)) {
			this.kernings.get(leading).put(following, offset);
		} else {
			final HashMap<FontCharacter, Integer> map = new HashMap<>();
			map.put(following, offset);
			this.kernings.put(following, map);
		}
	}

	public int getKerning(final FontCharacter leading, final FontCharacter following) {
		if (this.kernings.containsKey(leading)) {
			if (this.kernings.get(leading).containsKey(following)) {
				return this.kernings.get(leading).get(following);
			}
		}
		return 0;
	}
}
