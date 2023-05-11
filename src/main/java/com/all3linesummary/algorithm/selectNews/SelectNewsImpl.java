package com.all3linesummary.algorithm.selectNews;

import com.all3linesummary.algorithm.selectNews.dto.SelectedNews;
import com.all3linesummary.naverAPIs.searchNews.dto.NewsGetResult;
import com.all3linesummary.domain.News;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SelectNewsImpl implements SelectNews{

    @Autowired
    private SelectNewsFilterChain selectNewsFilterChain;

    @Override
    public List<SelectedNews> select(List<NewsGetResult> newsList) {
        List<SelectedNews> result = new ArrayList<>();
        for(NewsGetResult news : newsList){
            int point = selectNewsFilterChain.checkPointChain(news);
            if(point != SelectNewsFilterChain.FAIL_DIGIT){
                result.add(new SelectedNews(news, point));
            }
        }
        return result;
    }
}
