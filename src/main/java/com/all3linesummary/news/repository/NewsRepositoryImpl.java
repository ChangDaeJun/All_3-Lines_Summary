package com.all3linesummary.news.repository;

import com.all3linesummary.domain.News;
import com.all3linesummary.domain.NewsBody;
import com.all3linesummary.domain.NewsImage;
import com.all3linesummary.news.dto.NewsResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class NewsRepositoryImpl implements NewsRepository {

    private final NewsImageRepository newsImageRepository;
    private final NewsBodyRepository newsBodyRepository;

    @Override
    public NewsResponse getNewsResponse(Pageable pageable){
        Page<NewsBody> newsPage = newsBodyRepository.findAll(pageable);
        List<News> newsList = convertIntoNews(newsPage.getContent());
        return new NewsResponse(newsPage.getTotalPages(), newsList);
    }

    @Override
    @Transactional
    public News save(News news) {
        Long bodyId = saveNewsBody(news.getBody());
        saveNewsImage(news.getImageList(), bodyId);
        return news;
    }

    @Override
    public News findByNewsBodyId(Long id) {
        NewsBody newsBody = newsBodyRepository.findById(id).get();
        List<NewsImage> imageList = newsImageRepository.findByNewsId(id);
        return new News(newsBody, imageList);
    }

    private Long saveNewsBody(NewsBody body){
        newsBodyRepository.save(body);
        return body.getId();
    }

    private void saveNewsImage(List<NewsImage> imageList, Long bodyId){
        for(NewsImage image : imageList){
            image.setNewsId(bodyId);
            newsImageRepository.save(image);
        }
    }
    @Override
    @Transactional
    public void delete(News news) {
        deleteNewsBody(news.getBody());
        deleteNewsImage(news.getImageList());
    }

    private void deleteNewsBody(NewsBody body){
        newsBodyRepository.delete(body);
    }

    private void deleteNewsImage(List<NewsImage> imageList){
        for(NewsImage image : imageList){
            newsImageRepository.delete(image);
        }
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
