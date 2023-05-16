package com.all3linesummary.util.filter;

import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;

import java.util.ArrayList;
import java.util.List;

public abstract class FilterChain <T>{
    protected List<Filter<T>> filters;
    public int FAIL_DIGIT = Filter.FAIL_DIGIT;

    public FilterChain() {
        this.filters = new ArrayList<>();
    }
    public abstract int checkPointChain(T object);
}
