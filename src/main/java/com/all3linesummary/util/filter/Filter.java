package com.all3linesummary.util.filter;

public interface Filter<T> {
    int FAIL_DIGIT = -2_147_483_648;
    int checkPoint(T object);
}
