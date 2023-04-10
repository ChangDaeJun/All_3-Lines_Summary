package com.all3linesummary.news.newsCollector;

import com.all3linesummary.news.dto.NewsDTO;
import com.all3linesummary.news.entity.News;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SelectNewsImpl implements SelectNews{
    @Override
    public List<NewsDTO> select(JSONArray collectResult) {
        List<NewsDTO> result = new ArrayList<>();
        for(Object item : collectResult){
            NewsDTO news = getNewsByFilter((JSONObject) item);
            if(news != null) result.add(news);
        }
        return result;
    }

    private NewsDTO getNewsByFilter(JSONObject item) {
        if(checkNaverNews(item)) return null;
        if(checkKorean(item)) return null;

        NewsDTO newsDTO = crawlingNews(item);
        if(newsDTO == null) return null;

        int weight = getWeight(newsDTO);
        if(weight == -1) return null;
        newsDTO.setWeight(weight);
        return newsDTO;
    }

    private int getWeight(NewsDTO newsDTO) {
        int textLength = newsDTO.getNews().getTotalText();
        if(textLength < 500 || textLength >= 2000) return -1;
        else if(500 <= textLength && textLength < 650) return 1;
        else if(650 <= textLength && textLength < 800) return 2;
        else if(800 <= textLength && textLength < 950) return 4;
        else if(1100 <= textLength && textLength < 1250) return 7;
        else if(1250 <= textLength && textLength < 1400) return 7;
        else if(1400 <= textLength && textLength < 1550) return 7;
        else if(1550 <= textLength && textLength < 1700) return 4;
        else if(1700 <= textLength && textLength < 1850) return 2;
        else if(1850 <= textLength && textLength < 2000) return 1;

        return -1;
    }

    private NewsDTO crawlingNews(JSONObject item){
        String link = item.getString("link");
        String title = item.getString("title");
        Date date = new Date(System.currentTimeMillis());

        Element content = getContent(link);
        if(content == null) return null;
        String text = content.text();
        Elements images = content.select("img");
        News news = new News(title, link, text, date);
        return new NewsDTO(news, images);
    }


    private Element getContent(String url){
        Element result = null;

        try {
            Document doc = Jsoup.connect(url).get();
            result = doc.selectFirst("div#dic_area");
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    private boolean checkNaverNews(JSONObject item){
        String origin = item.getString("originallink");
        String link = item.getString("link");
        return link.equals(origin);
    }

    private boolean checkKorean(JSONObject item){
        return false;
    }
}
