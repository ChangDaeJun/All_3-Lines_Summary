package com.all3linesummary.algorithm.selectNews;

import com.all3linesummary.algorithm.selectNews.dto.SelectedNews;
import com.all3linesummary.naverAPIs.searchNews.SearchNewsService;
import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SelectNewsImplTestBody {
    @Autowired
    private SelectNews selectNews;
    @Autowired
    private SearchNewsService searchNewsService;
    @Test
    void select() {
        List<SearchedNews> list = searchNewsService.get(100, 1);
        List<SelectedNews> newsList = selectNews.select(list);
        for(SelectedNews news : newsList){
            System.out.println(news.toString());
        }
    }
}