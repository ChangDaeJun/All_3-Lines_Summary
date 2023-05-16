package com.all3linesummary.naverAPIs.searchNews.dto;

import lombok.ToString;
import org.json.JSONObject;

@ToString
public class SearchedNews {
    private String title;
    private String originalLink;
    private String naverLink;
    private String description;
    private String pubDate;

    public SearchedNews(JSONObject item) {
        this.title = item.getString("title");
        this.originalLink = item.getString("originallink");
        this.naverLink = item.getString("link");
        this.description = item.getString("description");
        this.pubDate = item.getString("pubDate");
    }

    public String getNaverLink(){
        return this.naverLink;
    }

    public int getTitleLength() {
        return title.length();
    }

    public boolean isNaverNews(){
        return !originalLink.equals(naverLink);
    }

    public String getTitle() {
        return title;
    }

    public String getPubDate() {
        return pubDate;
    }
}
