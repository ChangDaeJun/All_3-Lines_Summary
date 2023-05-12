package com.all3linesummary.news;

import com.all3linesummary.news.dto.NewsResponse;
import org.springframework.data.domain.Pageable;

public interface NewsReadService {
    NewsResponse getNewsResponse(Pageable pageable);
}
