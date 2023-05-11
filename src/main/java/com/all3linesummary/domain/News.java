package com.all3linesummary.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class News {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String URL;

    @Column
    private Date date;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(columnDefinition = "TEXT")
    private String text;

    public News(String title, String URL, String text, Date date) {
        this.title = title;
        this.URL = URL;
        this.text = text;
        this.date = date;
    }

    public int getTotalText(){
        return text.length() + title.length();
    }
}
