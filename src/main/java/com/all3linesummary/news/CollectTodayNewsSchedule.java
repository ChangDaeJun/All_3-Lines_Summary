package com.all3linesummary.news;

import com.all3linesummary.naverAPIs.searchNews.dto.NewsGetResult;
import com.all3linesummary.domain.News;
import com.all3linesummary.domain.NewsImage;
import com.all3linesummary.naverAPIs.searchNews.SearchNewsService;
import com.all3linesummary.algorithm.selectNews.SelectNews;
import com.all3linesummary.naverAPIs.summary.SummaryService;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Component
@AllArgsConstructor
public class CollectTodayNewsSchedule {
    private final NewsImageRepository newsImageRepository;
    private final NewsRepository newsRepository;
    private final SearchNewsService searchNewsService;
    private final SelectNews selectNews;
    private final SummaryService summaryService;

    public void collect(){
        PriorityQueue<NewsGetResult> newsRanking = new PriorityQueue<>(new Comparator<NewsGetResult>() {
            @Override
            public int compare(NewsGetResult o1, NewsGetResult o2) {
                return 0;//o1.getWeight() - o2.getWeight();
            }
        });

        for(int i = 1; i <= 1; i++){
            JSONArray news = null;//searchNewsService.get(100, i);
            List<NewsGetResult> selectedNews = selectNews.select(news);
            for(NewsGetResult newsGetResult : selectedNews){
                newsRanking.add(newsGetResult);
            }
        }

        int min = Math.min(newsRanking.size(), 1000);
        for(int i = 0; i < min; i++){
            NewsGetResult news = newsRanking.poll();
            String summary = "";//summaryService.summary(news.getNews().getTitle(), news.getNews().getText());
            //news.getNews().setSummary(summary);
            saveNews(news);
        }
    }

    private void saveNews(NewsGetResult newsGetResult){
        News news = null;//newsGetResult.getNews();
        List<String> images = null;//newsGetResult.getImages();
        newsRepository.save(news);

        Long newsId = news.getId();
        for(String image : images){
            NewsImage newsImage = new NewsImage(newsId, image);
            newsImageRepository.save(newsImage);
        }
    }
}
