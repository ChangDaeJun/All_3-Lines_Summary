package com.all3linesummary.algorithm.selectNews;

import com.all3linesummary.algorithm.selectNews.dto.SelectedNews;
import com.all3linesummary.algorithm.selectNews.filter.IsKoreanNewsFilter;
import com.all3linesummary.algorithm.selectNews.filter.IsNaverNewsFilter;
import com.all3linesummary.algorithm.selectNews.filter.PointByNumberOfCharFilter;
import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;
import com.all3linesummary.util.filter.Filter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SelectNewsImpl implements SelectNews{
    private final SelectNewsFilterChain selectNewsFilterChain;

    //@Autowired
    //public SelectNewsImpl(List<Filter<SearchedNews>> filters) {
    //    this.selectNewsFilterChain = new SelectNewsFilterChain(filters);
    //}

    public SelectNewsImpl() {
        List<Filter<SearchedNews>> list = new ArrayList<>();
        list.add(new IsNaverNewsFilter());
        list.add(new IsKoreanNewsFilter());
        list.add(new PointByNumberOfCharFilter());
        this.selectNewsFilterChain = new SelectNewsFilterChain(list);
    }

    @Override
    public List<SelectedNews> select(List<SearchedNews> newsList) {
        List<SelectedNews> result = new ArrayList<>();
        for(SearchedNews news : newsList){
            int point = selectNewsFilterChain.checkPointChain(news);
            if(point != selectNewsFilterChain.FAIL_DIGIT){
                result.add(new SelectedNews(news, point));
            }
        }
        return result;
    }
}
