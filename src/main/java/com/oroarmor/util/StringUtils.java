package com.oroarmor.util;

public class StringUtils {

	public static int countOf(String token, String search) {
		int count = 0;

		for (int i = search.indexOf(token); i <= Math.min(search.lastIndexOf(token),
				search.length() - token.length() + 1); i++) {
			if (search.substring(i, i + token.length()).equals(token)) {
				count++;
			}
		}
		return count;
	}
}
