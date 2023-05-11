package com.all3linesummary.naverAPIs.summary;


import com.all3linesummary.naverAPIs.summary.dto.Document;
import com.all3linesummary.naverAPIs.summary.dto.Option;

public interface SummaryService {
    String summary(Document document, Option option);
}
