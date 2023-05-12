package com.all3linesummary.news.dto;

import com.all3linesummary.domain.News;

import java.util.List;

public class NewsResponse {
    private int total;
    private List<News> newsList;

    public NewsResponse(int total, List<News> newsList) {
        this.total = total;
        this.newsList = newsList;
    }
}
