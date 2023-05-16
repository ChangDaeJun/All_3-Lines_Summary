package com.all3linesummary.news;

import com.all3linesummary.news.dto.NewsResponse;
import com.all3linesummary.news.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewsReadServiceImpl implements NewsReadService {
    private final NewsRepository newsRepository;
    @Override
    public NewsResponse getNewsResponse(Pageable pageable) {
        NewsResponse response = newsRepository.getNewsResponse(pageable);
        return response;
    }
}
