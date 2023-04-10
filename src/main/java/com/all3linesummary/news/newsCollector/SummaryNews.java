package com.all3linesummary.news.newsCollector;

import com.all3linesummary.news.dto.NewsDTO;

public interface SummaryNews {
    NewsDTO summary(NewsDTO newsDTO);
}
