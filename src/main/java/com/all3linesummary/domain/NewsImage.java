package com.all3linesummary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class NewsImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JsonIgnore
    private News news;

    @Column
    private String imageURL;

    public NewsImage(News news, String imageURL) {
        this.news = news;
        this.imageURL = imageURL;
    }
}
