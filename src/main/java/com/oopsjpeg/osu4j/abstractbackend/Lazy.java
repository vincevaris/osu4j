package com.oopsjpeg.osu4j.abstractbackend;

import com.oopsjpeg.osu4j.exception.OsuAPIException;

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a result that is lazily retrieved from the API. Once loaded, the result is cached either in the cache
 * given, or in a {@link SimpleItemCache} if no cache is given.
 *
 * @param <R> the result type
 * @author WorldSEnder
 */
public class Lazy<R> {
	private final HideQueryType<?, R> hiddenQuery;

	public Lazy(Query<R> query) {
		this(query, new SimpleItemCache<>());
	}

	public <T> Lazy(Query<T> query, MappedCache<T, R> cache) {
		this.hiddenQuery = new HideQueryType<>(query, cache);
	}

	private Lazy(HideQueryType<?, R> hiddenQuery) {
		this.hiddenQuery = hiddenQuery;
	}

	public R get() throws OsuAPIException {
		return hiddenQuery.get();
	}

	public <S> Lazy<S> map(Function<R, S> mapper) {
		HideQueryType<?, S> mappedHiddenQuery = hiddenQuery.map(mapper);
		return new Lazy<>(mappedHiddenQuery);
	}

	private static class HideQueryType<T, R> {
		private final Query<T> query;
		private final MappedCache<T, R> resultCache;

		protected HideQueryType(Query<T> query, MappedCache<T, R> resultCache) {
			this.query = Objects.requireNonNull(query);
			this.resultCache = Objects.requireNonNull(resultCache);
		}

		public R get() throws OsuAPIException {
			return resultCache.getOrLoadChecked(query::resolve);
		}

		public <S> HideQueryType<T, S> map(Function<R, S> mapper) {
			return new HideQueryType<>(query, resultCache.map(mapper));
		}
	}
}
