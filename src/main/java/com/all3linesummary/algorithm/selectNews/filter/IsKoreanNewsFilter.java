package com.all3linesummary.algorithm.selectNews.filter;

import com.all3linesummary.naverAPIs.searchNews.dto.NewsGetResult;
import com.all3linesummary.util.filter.Filter;

public class IsKoreanNewsFilter implements Filter<NewsGetResult> {

    @Override
    public int checkPoint(NewsGetResult object) {
        return 0;
    }
}
