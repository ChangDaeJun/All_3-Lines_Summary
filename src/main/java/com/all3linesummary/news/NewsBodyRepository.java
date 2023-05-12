package com.all3linesummary.news;

import com.all3linesummary.domain.NewsBody;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsBodyRepository extends JpaRepository<NewsBody, Long> {
}
