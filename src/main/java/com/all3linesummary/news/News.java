package com.all3linesummary.news;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class News {
    @Id
    private Long id;

    @Column
    private String pressName;

    @Column
    private String title;

    @Column
    private String URL;

    @Column
    private Date date;

    @Column
    private String summary;
}
