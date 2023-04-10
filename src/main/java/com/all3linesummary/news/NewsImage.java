package com.all3linesummary.news;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class NewsImage {
    @Id
    private Long id;

    @Column
    private Long newsId;

    @Column
    private String imageURL;
}
