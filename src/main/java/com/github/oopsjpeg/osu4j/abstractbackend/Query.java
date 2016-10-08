package com.github.oopsjpeg.osu4j.abstractbackend;

import java.util.Objects;

import com.github.oopsjpeg.osu4j.exception.OsuAPIException;

/**
 * Represents an endpoint where arguments are already given. Abstracts out the argument class.
 * 
 * @author WorldSEnder
 *
 * @param <R>
 *            the result type.
 */
public final class Query<R> {
    private static class EndpointWithArguments<A, R> {
        private final Endpoint<A, R> endpoint;
        private final A arguments;

        public EndpointWithArguments(Endpoint<A, R> endPoint, A arguments) {
            this.endpoint = Objects.requireNonNull(endPoint);
            this.arguments = Objects.requireNonNull(arguments);
        }

        public R resolve() throws OsuAPIException {
            return endpoint.query(arguments);
        }

        @Override
        public String toString() {
            return endpoint.toString() + "(" + arguments.toString() + ")";
        }
    }

    private final EndpointWithArguments<?, R> filledEndpoint;

    public <A> Query(Endpoint<A, R> endpoint, A arguments) {
        filledEndpoint = new EndpointWithArguments<A, R>(endpoint, arguments);
    }

    public R resolve() throws OsuAPIException {
        return filledEndpoint.resolve();
    }

    public LazilyLoaded<R> asLazilyLoaded() {
        return new LazilyLoaded<>(this);
    }

    public LazilyLoaded<R> getLazyResult(Cache<R> cacheToUse) {
        return new LazilyLoaded<>(this, cacheToUse);
    }

    @Override
    public String toString() {
        return "Query[" + filledEndpoint.toString() + "]";
    }
}
