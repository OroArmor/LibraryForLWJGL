package com.oroarmor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ResourceLoader {

	public static String loadFile(InputStream resourceAsStream) {
		String fileString = "";
		try {
			byte[] fileBytes = new byte[resourceAsStream.available()];
			resourceAsStream.read(fileBytes);
			fileString = new String(fileBytes);
			resourceAsStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileString;
	}

	public static String loadFile(String filePath) {
		String fileString = "";
		File file = new File(filePath);

		try {
			fileString = loadFile(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileString;
	}
}
