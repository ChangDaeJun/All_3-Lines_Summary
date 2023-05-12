package com.all3linesummary.algorithm.selectNews.dto;

import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;
import lombok.ToString;

import java.util.Comparator;

@ToString
public class SelectedNews implements Comparable<SelectedNews>{
    private SearchedNews news;
    private int point;

    public SelectedNews(SearchedNews news, int point) {
        this.news = news;
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    @Override
    public int compareTo(SelectedNews o) {
        return this.point - o.getPoint();
    }

    public String getURL(){
        return this.news.getNaverLink();
    }

    public String getTitle(){
        return this.news.getTitle();
    }
}
