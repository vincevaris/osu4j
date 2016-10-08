package com.github.oopsjpeg.osu4j.backend;

/**
 * Capsule for the user-token. Storing it as a raw string may lead to it being printed unexpectedly much like a
 * password-input field.
 * 
 * @author WorldSEnder
 *
 */
public final class OsuToken {
    private String token;

    public OsuToken(String token) {
        this.token = verifyToken(token);
    }

    /**
     * Gets the token in its raw form, e.g. to append it to http-parameters.
     * 
     * @return the token
     */
    public String getTokenRaw() {
        return token;
    }

    private String verifyToken(String token) {
        if (token == null) {
            throw new IllegalArgumentException("token can't be null");
        }
        // TODO: add more verification??
        return token;
    }

    @Override
    public int hashCode() {
        return token.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OsuToken) {
            return this.token.equals(((OsuToken) obj).token);
        }
        return false;
    }

    @Override
    public String toString() {
        return "OsuToken[<scribbled for security>]";
    }

}
