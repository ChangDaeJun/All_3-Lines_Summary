package com.all3linesummary.news;

import com.all3linesummary.news.dto.NewsDTO;
import com.all3linesummary.news.entity.News;
import com.all3linesummary.news.entity.NewsImage;
import com.all3linesummary.news.newsCollector.CollectNews;
import com.all3linesummary.news.newsCollector.SelectNews;
import com.all3linesummary.news.newsCollector.SummaryNews;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.PriorityQueue;

@Component
@AllArgsConstructor
public class CollectTodayNewsSchedule {
    private final NewsImageRepository newsImageRepository;
    private final NewsRepository newsRepository;
    private final CollectNews collectNews;
    private final SelectNews selectNews;
    private final SummaryNews summaryNews;

    public void collect(){
        PriorityQueue<NewsDTO> newsRanking = new PriorityQueue<>();
        for(int i = 1; i <= 1; i++){
            JSONArray news = collectNews.get(100, i);
            List<NewsDTO> selectedNews = selectNews.select(news);
            for(NewsDTO newsDTO : selectedNews){
                newsRanking.add(newsDTO);
            }
        }

        /*
        int min = Math.min(newsRanking.size(), 1000);
        for(int i = 0; i < min; i++){
            NewsDTO summary = summaryNews.summary(newsRanking.poll());
            saveNews(summary);
        }*/
    }

    private void saveNews(NewsDTO newsDTO){
        News news = newsDTO.getNews();
        List<String> images = newsDTO.getImages();
        newsRepository.save(news);

        Long newsId = news.getId();
        for(String image : images){
            NewsImage newsImage = new NewsImage(newsId, image);
            newsImageRepository.save(newsImage);
        }
    }
}
