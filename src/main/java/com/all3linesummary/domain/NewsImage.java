package com.all3linesummary.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
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

    public NewsImage(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }
}
