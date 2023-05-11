package com.all3linesummary.naverAPIs.searchNews;

import com.all3linesummary.naverAPIs.searchNews.dto.NewsGetResult;
import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchNewsServiceTest {

    @Autowired
    private SearchNewsService searchNewsService;

    @Test
    public void 뉴스_검색(){
        List<NewsGetResult> list = searchNewsService.get(10, 1);
        System.out.println(list.size());
        for(NewsGetResult news : list){
            System.out.println(news.toString());
        }
    }
}