package com.github.oopsjpeg.osu4j.abstractbackend;

/**
 * A cache is responsible for managing one item. It may offer functionality to expire the cache after some time, to
 * reset the cache, to replace the cache or other ways to manipulate the content.<br>
 * It guarantees that the implementation is thread-safe. It is also guaranteed that the implementation will not change
 * state if one of the suppliers given throws during execution, and the exception thrown will be passed on unmodified.
 * 
 * @author WorldSEnder
 *
 * @param <T>
 *            the type of the managed item
 */
public interface Cache<T> extends MappedCache<T, T> {
}
