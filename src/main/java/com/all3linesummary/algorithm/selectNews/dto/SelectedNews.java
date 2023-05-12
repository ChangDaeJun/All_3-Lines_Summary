package com.all3linesummary.algorithm.selectNews.dto;

import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;
import lombok.ToString;

@ToString
public class SelectedNews {
    private SearchedNews news;
    private int point;

    public SelectedNews(SearchedNews news, int point) {
        this.news = news;
        this.point = point;
    }
}
