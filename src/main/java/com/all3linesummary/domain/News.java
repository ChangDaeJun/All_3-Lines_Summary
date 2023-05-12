package com.all3linesummary.domain;

import java.util.List;

public class News {
    private NewsBody body;
    private List<NewsImage> imageList;

    public News(NewsBody body, List<NewsImage> imageList) {
        this.body = body;
        this.imageList = imageList;
    }

    public NewsBody getBody() {
        return body;
    }

    public List<NewsImage> getImageList() {
        return imageList;
    }
}
