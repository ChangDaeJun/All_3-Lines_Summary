package com.all3linesummary.news;

import com.all3linesummary.algorithm.selectNews.dto.SelectedNews;
import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;
import com.all3linesummary.naverAPIs.searchNews.SearchNewsService;
import com.all3linesummary.algorithm.selectNews.SelectNews;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CollectTodayNewsSchedule {

    private final SearchNewsService searchNewsService;
    private final SelectNews selectNews;

    private static final int REQUEST_NUMBER = 6_000;
    private static final int DISPLAY_SIZE = 100;

    public void collectTodayNews(){
        List<SearchedNews> todayNewsList = requestTodayNews(REQUEST_NUMBER);
        List<SelectedNews> selectedNewsList = selectNews.select(todayNewsList);
        sortByPoint(selectedNewsList);

        saveNews(selectedNewsList);
    }



    public List<SearchedNews> requestTodayNews(int number){
        List<SearchedNews> newsGetList = new ArrayList<>(number);
        int cnt = (int) Math.ceil(number / 100);
        for(int i = 1; i <= cnt; i++){
            List<SearchedNews> searchResult = searchNewsService.get(DISPLAY_SIZE, i);
            newsGetList.addAll(newsGetList);
        }
        return newsGetList;
    }
}
