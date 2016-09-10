package com.github.oopsjpeg.osu4j.util;

public class OsuRateLimitException extends Exception {

	private static final long serialVersionUID = 2007081453956527716L;
	
	public OsuRateLimitException(long requestsPerMinute) {
		super("You have hit the rate limit for requests: " + requestsPerMinute + " per minute");
	}
	
}
