package com.all3linesummary.news.dto;

import com.all3linesummary.news.entity.News;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NewsDTO{
    private News news;
    private List<String> images;
    private int weight;

    public NewsDTO(News news, Elements images) {
        this.news = news;
        this.images = new ArrayList<>();
        for (Element image : images) {
            String imageUrl = image.attr("data-src");
            this.images.add(imageUrl);
        }
    }
}
