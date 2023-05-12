package com.all3linesummary.util.filter;

import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;

import java.util.ArrayList;
import java.util.List;

public interface FilterChain <T>{
    List<Filter<SearchedNews>> filters = new ArrayList<>();
    int FAIL_DIGIT = -2_147_483_648;
    int checkPointChain(T object);
}
