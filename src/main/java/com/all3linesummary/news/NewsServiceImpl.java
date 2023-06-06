package com.all3linesummary.news;

import com.all3linesummary.domain.News;
import com.all3linesummary.news.dto.NewsResponse;
import com.all3linesummary.news.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;

    @Override
    public NewsResponse getNews(Pageable pageable) {
        Page<News> newsPage = newsRepository.findAll(pageable);
        return new NewsResponse(newsPage.getTotalPages(), newsPage.getContent());
    }
}
