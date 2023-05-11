package com.all3linesummary.summary.dto;

public class SummaryRequest {
    private Document document;
    private Option option;

    public SummaryRequest(Document document, Option option) {
        this.document = document;
        this.option = option;
    }
}
