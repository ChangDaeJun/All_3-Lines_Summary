package com.all3linesummary.algorithm.selectNews.filter;

import com.all3linesummary.naverAPIs.searchNews.dto.NewsGetResult;
import com.all3linesummary.util.filter.Filter;

public class IsNaverNewsFilter implements Filter<NewsGetResult> {
    @Override
    public int checkPoint(NewsGetResult news) {
        if(news.isNaverNews()) return 0;
        else return FAIL_DIGIT;
    }
}
