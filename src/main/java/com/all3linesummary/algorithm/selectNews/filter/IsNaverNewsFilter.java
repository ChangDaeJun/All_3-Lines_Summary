package com.all3linesummary.algorithm.selectNews.filter;

import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;
import com.all3linesummary.util.filter.Filter;

public class IsNaverNewsFilter implements Filter<SearchedNews> {
    @Override
    public int checkPoint(SearchedNews news) {
        if(news.isNaverNews()) return 0;
        else return FAIL_DIGIT;
    }
}
