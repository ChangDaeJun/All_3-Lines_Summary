package com.all3linesummary.news;

import com.all3linesummary.core.selectNews.dto.SelectedNews;
import com.all3linesummary.domain.News;
import com.all3linesummary.domain.NewsImage;
import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;
import com.all3linesummary.naverAPIs.searchNews.SearchNewsService;
import com.all3linesummary.core.selectNews.SelectNews;
import com.all3linesummary.naverAPIs.summary.SummaryService;
import com.all3linesummary.naverAPIs.summary.dto.Document;
import com.all3linesummary.naverAPIs.summary.dto.Option;
import com.all3linesummary.news.repository.NewsImageRepository;
import com.all3linesummary.news.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class CollectTodayNewsSchedule {

    private final SearchNewsService searchNewsService;
    private final SelectNews selectNews;
    private final SummaryService summaryService;
    private final NewsRepository newsRepository;
    private final NewsImageRepository newsImageRepository;
    private static final int REQUEST_NUMBER = 1000; //6_000;
    private static final int DISPLAY_SIZE = 100;
    private static final int MAX_SUMMARY_SIZE = 50;//1_000;

    //@Scheduled(cron ="0 0 12 1/1 * ?")
    @Transactional
    public void collectTodayNews(){
        List<SearchedNews> todaySearchedNewsList = searchNewsService.requestTodayNews(DISPLAY_SIZE, REQUEST_NUMBER);
        List<SelectedNews> todaySelectedNewsList = selectNews.select(todaySearchedNewsList);
        Collections.sort(todaySelectedNewsList);
        saveNews(todaySelectedNewsList);
    }

    private void saveNews(List<SelectedNews> newsList){
        int summaryCnt = Math.min(newsList.size(), MAX_SUMMARY_SIZE);
        for(int i = 0; i < summaryCnt; i++){
            try {
                SelectedNews selectedNews = newsList.get(i);
                Element content = getContent(selectedNews.getURL());
                News news = getNews(selectedNews, content);

                newsRepository.save(news);

                List<NewsImage> imageList = getNewsImage(news, content);
                for(NewsImage image : imageList){
                    newsImageRepository.save(image);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private List<NewsImage> getNewsImage(News news, Element content){
        List<NewsImage> imageList = new ArrayList<>();
        Elements images = content.select("img");
        for (Element image : images) {
            String imageUrl = image.attr("data-src");
            NewsImage newsImage = new NewsImage(news, imageUrl);
            imageList.add(newsImage);
        }
        return imageList;
    }

    private News getNews(SelectedNews selectedNews, Element content){
        String text = content.text();
        Document document = new Document(text, selectedNews.getTitle());
        Option option = Option.getDefaultNews();
        String summary = summaryService.summary(document, option);
        return new News(selectedNews, summary);
    }

    private Element getContent(String url) throws IOException {
        org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
        Element result = doc.selectFirst("div#dic_area");
        return result;
    }
}
