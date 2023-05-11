package com.all3linesummary.util.filter;

import com.all3linesummary.naverAPIs.searchNews.dto.NewsGetResult;

import java.util.ArrayList;
import java.util.List;

public interface FilterChain <T>{
    List<Filter<NewsGetResult>> filters = new ArrayList<>();
    int FAIL_DIGIT = -2_147_483_648;
    int checkPointChain(T object);
}
