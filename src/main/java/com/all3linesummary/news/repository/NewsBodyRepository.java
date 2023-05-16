package com.all3linesummary.news.repository;

import com.all3linesummary.domain.NewsBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsBodyRepository extends JpaRepository<NewsBody, Long> {
    Page<NewsBody> findAll(Pageable pageable);
}
