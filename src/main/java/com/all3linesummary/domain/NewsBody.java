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


    public NewsBody(String title, String URL, Date date, String summary, String text) {
        this.title = title;
        this.URL = URL;
        this.summary = summary;
    }

    public NewsBody(SelectedNews selectedNews, String summary){
        this.title = selectedNews.getTitle();
        this.URL = selectedNews.getURL();
        this.summary = summary;
    }
}
