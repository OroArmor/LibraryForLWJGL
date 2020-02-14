package com.oroarmor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * This class loads text files and returns them as a string.
 * 
 * @author OroArmor
 *
 */
public class ResourceLoader {

	/**
	 * 
	 * @param resourceAsStream The input stream for the file
	 * @return The data in the string returned as a file
	 */
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

	/**
	 * 
	 * @param filePath A string to the file
	 * @return The data of the file as a string
	 */
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

	/**
	 * No instances for you
	 */
	private ResourceLoader() {
	}
}
