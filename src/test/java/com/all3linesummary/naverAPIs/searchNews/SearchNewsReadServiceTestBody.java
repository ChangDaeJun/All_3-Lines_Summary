package com.all3linesummary.naverAPIs.searchNews;

import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SearchNewsReadServiceTestBody {

    @Autowired
    private SearchNewsService searchNewsService;

    @Test
    public void 뉴스_검색(){
        for(int i = 1; i < 100; i+= 10){
            List<SearchedNews> list = searchNewsService.get(10, i);
            System.out.println(list.size());
            for(SearchedNews news : list){
                System.out.println(news.toString());
            }
        }
    }
}