package com.all3linesummary.core.selectNews.filter;

import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;
import com.all3linesummary.util.filter.Filter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PointByNumberOfCharFilter implements Filter<SearchedNews> {
    @Override
    public int checkPoint(SearchedNews news) {
        int contextLength = getNewsContentLength(news.getNaverLink());
        int titleLength = news.getTitleLength();
        return getPoint(contextLength + titleLength);
    }

    private int getPoint(int textLength) {
        if(textLength < 500 || textLength >= 2000) return FAIL_DIGIT;
        else if(500 <= textLength && textLength < 650) return 1;
        else if(650 <= textLength && textLength < 800) return 2;
        else if(800 <= textLength && textLength < 950) return 4;
        else if(1100 <= textLength && textLength < 1250) return 7;
        else if(1250 <= textLength && textLength < 1400) return 7;
        else if(1400 <= textLength && textLength < 1550) return 7;
        else if(1550 <= textLength && textLength < 1700) return 4;
        else if(1700 <= textLength && textLength < 1850) return 2;
        else if(1850 <= textLength && textLength < 2000) return 1;
        return FAIL_DIGIT;
    }

    private int getNewsContentLength(String link){
        Element content = getContent(link);
        if(content == null) return FAIL_DIGIT;
        String text = content.text();
        return text.length();
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
}
