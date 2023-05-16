package com.all3linesummary.news;

import com.all3linesummary.domain.News;
import com.all3linesummary.news.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class NewsDeleteServiceImpl implements NewsDeleteService{
    private final NewsRepository newsRepository;
    @Override
    @Transactional
    public void delete(Long bodyId) {
        News news = newsRepository.findByNewsBodyId(bodyId);
        newsRepository.delete(news);
    }
}
