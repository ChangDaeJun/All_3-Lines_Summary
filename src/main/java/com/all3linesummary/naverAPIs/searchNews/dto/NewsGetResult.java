package com.all3linesummary.naverAPIs.searchNews.dto;

import lombok.ToString;
import org.json.JSONObject;

@ToString
public class NewsGetResult {
    private String title;
    private String originalLink;
    private String naverLink;
    private String description;
    private String pubDate;

    public NewsGetResult(JSONObject item) {
        this.title = item.getString("title");
        this.originalLink = item.getString("originallink");
        this.naverLink = item.getString("link");
        this.description = item.getString("description");
        this.pubDate = item.getString("pubDate");
    }
}
