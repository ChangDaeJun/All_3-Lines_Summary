package com.all3linesummary.domain;

import com.all3linesummary.core.selectNews.dto.SelectedNews;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class News {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NEWS_ID")
    @JsonIgnore
    private Long id;

    @Column
    private String title;

    @Column
    private String URL;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column
    private String pubDate;

    @OneToMany(mappedBy = "news", cascade = CascadeType.REMOVE)
    private List<NewsImage> images;

    public News(SelectedNews selectedNews, String summary){
        this.title = selectedNews.getTitle();
        this.URL = selectedNews.getURL();
        this.summary = summary;
        this.pubDate = selectedNews.getPubDate();
    }
}
