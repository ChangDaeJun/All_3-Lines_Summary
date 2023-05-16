package com.all3linesummary.domain;

import com.all3linesummary.algorithm.selectNews.dto.SelectedNews;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class NewsBody {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String URL;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column
    private String pubDate;

    public NewsBody(SelectedNews selectedNews, String summary){
        this.title = selectedNews.getTitle();
        this.URL = selectedNews.getURL();
        this.summary = summary;
        this.pubDate = selectedNews.getPubDate();
    }
}
