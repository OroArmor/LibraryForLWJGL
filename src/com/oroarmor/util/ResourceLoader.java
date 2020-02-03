package com.oroarmor.util;

import java.io.File;
import java.io.FileInputStream;

public class ResourceLoader {

	public static String loadFile(String filePath) {
		String fileString = "";
		File file = new File(filePath);

		try {
			FileInputStream fileStream = new FileInputStream(file);
			byte[] fileBytes = new byte[fileStream.available()];
			fileStream.read(fileBytes);
			fileString = new String(fileBytes);
			fileStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileString;
	}
}
