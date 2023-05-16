package com.all3linesummary.news.repository;

import com.all3linesummary.domain.News;
import com.all3linesummary.news.dto.NewsResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsRepository {
    NewsResponse getNewsResponse(Pageable pageable);
    News save(News news);
    News findByNewsBodyId(Long id);
    void delete(News news);
}
