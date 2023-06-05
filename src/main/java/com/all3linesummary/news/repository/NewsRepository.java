package com.all3linesummary.news.repository;

import com.all3linesummary.domain.News;
import com.all3linesummary.news.dto.NewsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
    //Page<News> findAll(Pageable pageable);
    //NewsResponse getNewsResponse(Pageable pageable);
}
