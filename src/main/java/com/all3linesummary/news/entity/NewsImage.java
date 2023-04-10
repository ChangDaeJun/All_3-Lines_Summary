package com.all3linesummary.news.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class NewsImage {
    @Id
    private Long id;

    @Column
    private Long newsId;

    @Column
    private String imageURL;

    public NewsImage(Long newsId, String imageURL) {
        this.newsId = newsId;
        this.imageURL = imageURL;
    }
}
