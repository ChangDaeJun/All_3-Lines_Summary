package com.all3linesummary.algorithm.selectNews;

import com.all3linesummary.algorithm.selectNews.dto.SelectedNews;
import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class SelectNewsImpl implements SelectNews{

    private final SelectNewsFilterChain selectNewsFilterChain;

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
