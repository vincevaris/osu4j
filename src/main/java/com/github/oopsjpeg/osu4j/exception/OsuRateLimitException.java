package com.github.oopsjpeg.osu4j.exception;

import com.github.oopsjpeg.osu4j.exception.OsuAPIException;

public class OsuRateLimitException extends OsuAPIException {

    private static final long serialVersionUID = 2007081453956527716L;

    public OsuRateLimitException(long requestsPerMinute) {
        super("You have hit the rate limit for requests: " + requestsPerMinute + " per minute");
    }

}
