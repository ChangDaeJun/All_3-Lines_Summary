package com.all3linesummary.core.selectNews;

import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;
import com.all3linesummary.util.filter.Filter;
import com.all3linesummary.util.filter.FilterChain;

import java.util.List;


public class SelectNewsFilterChain extends FilterChain<SearchedNews> {

    public SelectNewsFilterChain(List<Filter<SearchedNews>> filterList) {
        for(Filter<SearchedNews> filter : filterList){
            filters.add(filter);
        }
    }

    @Override
    public int checkPointChain(SearchedNews news) {
        int point = 0;
        for(Filter filter : filters){
            int cur = filter.checkPoint(news);
            if(cur == FAIL_DIGIT) return FAIL_DIGIT;
            point += cur;
        }
        return point;
    }
}
