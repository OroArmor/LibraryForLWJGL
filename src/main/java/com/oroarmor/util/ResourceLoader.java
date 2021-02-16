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
public final class ResourceLoader {
	/**
	 *
	 * @param resourceAsStream The input stream for the file
	 * @return The data in the file returned as a string
	 */
	public static String loadFileString(InputStream resourceAsStream) {
		return new String(loadFileBytes(resourceAsStream));
	}

	/**
	 *
	 * @param resourceAsStream The input stream for the file
	 * @return The data in the file returned as a string
	 */
	public static byte[] loadFileBytes(InputStream resourceAsStream) {
		try {
			byte[] fileBytes = new byte[resourceAsStream.available()];
			int readBytes = resourceAsStream.read(fileBytes);
			if(readBytes == -1) {
				throw new Exception("Could not read InputStream");
			}
			resourceAsStream.close();
			return fileBytes;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new byte[0];
	}

	/**
	 *
	 * @param filePath A string to the file
	 * @return The data of the file as a string
	 */
	public static String loadFileString(String filePath) {
		String fileString = "";
		File file = new File(filePath);

		try {
			fileString = loadFileString(new FileInputStream(file));
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileString;
	}
}
