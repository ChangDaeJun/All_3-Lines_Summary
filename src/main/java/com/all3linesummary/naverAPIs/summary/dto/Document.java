package com.all3linesummary.naverAPIs.summary.dto;

import lombok.ToString;

public class Document {
    private String title;
    private String content;

    public Document(String content, String title) {
        this.content = content;
        this.title = title;
    }

    public Document(String content) {
        this.content = content;
    }
}
