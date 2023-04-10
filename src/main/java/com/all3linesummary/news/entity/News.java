package com.all3linesummary.news.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class News {
    @Id
    private Long id;

    @Column
    private String title;

    @Column
    private String URL;

    @Column
    private Date date;

    @Column
    private String summary;

    @Column
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
