package com.fenbi.ytklearn.utils;

/**
 * Created by lishaozhe on 17-7-27.
 */
public interface MyFunction<T, R> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);
}
