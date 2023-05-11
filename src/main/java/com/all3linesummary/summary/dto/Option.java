package com.all3linesummary.summary.dto;

import lombok.ToString;

public class Option {
    private String language;
    private String model;
    private int tone;
    private int summaryCount;

    public Option(String language) {
        this.language = language;
    }

    public Option(String language, String model) {
        this.language = language;
        this.model = model;
    }

    public Option(String language, String model, int tone) {
        this.language = language;
        this.model = model;
        this.tone = tone;
    }

    public Option(String language, String model, int tone, int summaryCount) {
        this.language = language;
        this.model = model;
        this.tone = tone;
        this.summaryCount = summaryCount;
    }
}
