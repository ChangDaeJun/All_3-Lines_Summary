package com.all3linesummary.summary;

import com.all3linesummary.news.dto.NewsDTO;

public interface SummaryService {
    String summary(String title, String text);
}