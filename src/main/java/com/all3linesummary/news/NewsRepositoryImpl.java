package com.all3linesummary.news;

import com.all3linesummary.domain.News;
import com.all3linesummary.domain.NewsBody;
import com.all3linesummary.domain.NewsImage;
import com.all3linesummary.news.dto.NewsResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class NewsRepositoryImpl implements NewsRepository{

    private final NewsImageRepository newsImageRepository;
    private final NewsBodyRepository newsBodyRepository;

    @Override
    public NewsResponse getNewsResponse(Pageable pageable){
        Page<NewsBody> newsPage = newsBodyRepository.findAll(pageable);
        List<News> newsList = convertIntoNews(newsPage.getContent());
        return new NewsResponse(newsPage.getTotalPages(), newsList);
    }

    private List<News> convertIntoNews(List<NewsBody> bodyList){
        List<News> newsList = new ArrayList<>(bodyList.size());
        for(NewsBody body : bodyList){
            List<NewsImage> imageList = newsImageRepository.findByNewsId(body.getId());
            newsList.add(new News(body, imageList));
        }
        return newsList;
    }
}
