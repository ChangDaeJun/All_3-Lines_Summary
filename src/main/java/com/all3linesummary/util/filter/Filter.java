package com.all3linesummary.util.filter;

public interface Filter<T> {
    int FAIL_DIGIT = FilterChain.FAIL_DIGIT;
    int checkPoint(T object);
}
