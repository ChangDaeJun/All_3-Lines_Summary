package com.all3linesummary.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class NewsImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private News news;

    @Column
    private String imageURL;

    public NewsImage(News news, String imageURL) {
        this.news = news;
        this.imageURL = imageURL;
    }
}
