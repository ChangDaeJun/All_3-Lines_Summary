package com.all3linesummary.news.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class NewsImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
