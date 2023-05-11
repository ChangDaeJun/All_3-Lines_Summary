package com.all3linesummary.news;

import com.all3linesummary.domain.NewsImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsImageRepository extends JpaRepository<NewsImage, Long> {
}
