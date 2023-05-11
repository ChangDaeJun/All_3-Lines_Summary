package com.all3linesummary.algorithm.selectNews.dto;

import com.all3linesummary.naverAPIs.searchNews.dto.NewsGetResult;
import lombok.ToString;

@ToString
public class SelectedNews {
    private NewsGetResult news;
    private int point;

    public SelectedNews(NewsGetResult news, int point) {
        this.news = news;
        this.point = point;
    }
}
