package com.github.oopsjpeg.osu4j.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Utility {

	public static String readStream(String url) throws IOException {
		return readStream(new URL(url).openStream());
	}
	
	public static String readStream(URL url) throws IOException {
		return readStream(url.openStream());
	}
	
	public static String readStream(InputStream stream) throws IOException {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(stream))){
			StringBuilder builder = new StringBuilder();
			int read;
			char[] chars = new char[1024];
			while ((read = br.read(chars)) != -1)
				builder.append(chars, 0, read);
			return builder.toString();
		}
	}
	
}
