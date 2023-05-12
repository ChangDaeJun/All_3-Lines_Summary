package com.all3linesummary.algorithm.selectNews.filter;

import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;
import com.all3linesummary.util.filter.Filter;

public class IsKoreanNewsFilter implements Filter<SearchedNews> {

    @Override
    public int checkPoint(SearchedNews object) {
        return 0;
    }
}
