package com.github.oopsjpeg.osu4j.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Utility {

	public static String readUrl(String urlString) throws IOException {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuilder builder = new StringBuilder();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				builder.append(chars, 0, read);

			return builder.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}
	
}
