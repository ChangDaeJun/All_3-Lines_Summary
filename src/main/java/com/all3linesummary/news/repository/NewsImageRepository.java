package com.all3linesummary.news.repository;

import com.all3linesummary.domain.NewsImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsImageRepository extends JpaRepository<NewsImage, Long> {
    List<NewsImage> findByNewsId(Long NewsId);
}
