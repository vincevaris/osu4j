package com.oopsjpeg.osu4j.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Utility {

	// peppy's server offset I'd guess
	private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder().parseCaseInsensitive()
			.appendPattern("u-M-d H:m:s").toFormatter().withZone(ZoneId.of("UTC+8"));

	public static ZonedDateTime parseDate(String dayFromApi) {
		if (dayFromApi == null) {
			// Actually bad practice, but we shall allow it for /api/get_match
			return null;
		}
		return ZonedDateTime.parse(dayFromApi, FORMATTER);
	}

	public static String toMySqlString(ZonedDateTime date) {
		if (date == null) {
			return null;
		}
		return date.format(FORMATTER);
	}

	public static String urlEncode(String argument) {
		try {
			return URLEncoder.encode(argument, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
