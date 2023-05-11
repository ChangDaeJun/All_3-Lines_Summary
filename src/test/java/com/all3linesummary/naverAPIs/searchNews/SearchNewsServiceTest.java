package com.all3linesummary.naverAPIs.searchNews;

import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchNewsServiceTest {

    @Autowired
    private SearchNewsService searchNewsService;

    @Test
    public void 뉴스_검색(){
        JSONArray array = searchNewsService.get(10, 1);
    }
}