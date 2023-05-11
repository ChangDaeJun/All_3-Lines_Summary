package com.all3linesummary.summary;


import com.all3linesummary.summary.dto.Document;
import com.all3linesummary.summary.dto.Option;

public interface SummaryService {
    String summary(Document document, Option option);
}
