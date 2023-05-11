package com.all3linesummary.algorithm.selectNews;

import com.all3linesummary.algorithm.selectNews.filter.IsKoreanNewsFilter;
import com.all3linesummary.algorithm.selectNews.filter.IsNaverNewsFilter;
import com.all3linesummary.algorithm.selectNews.filter.PointByNumberOfCharFilter;
import com.all3linesummary.naverAPIs.searchNews.dto.NewsGetResult;
import com.all3linesummary.util.filter.Filter;
import com.all3linesummary.util.filter.FilterChain;
import org.springframework.stereotype.Component;

@Component
public class SelectNewsFilterChain implements FilterChain<NewsGetResult> {

    public SelectNewsFilterChain() {
        filters.add(new IsNaverNewsFilter());
        filters.add(new IsKoreanNewsFilter());
        filters.add(new PointByNumberOfCharFilter());
    }

    @Override
    public int checkPointChain(NewsGetResult news) {
        int point = 0;
        for(Filter filter : filters){
            int cur = filter.checkPoint(news);
            if(cur == FAIL_DIGIT) return FAIL_DIGIT;
            point += cur;
        }
        return point;
    }
}
