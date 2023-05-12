package com.all3linesummary.news;

import com.all3linesummary.algorithm.selectNews.dto.SelectedNews;
import com.all3linesummary.domain.News;
import com.all3linesummary.domain.NewsBody;
import com.all3linesummary.domain.NewsImage;
import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;
import com.all3linesummary.naverAPIs.searchNews.SearchNewsService;
import com.all3linesummary.algorithm.selectNews.SelectNews;
import com.all3linesummary.naverAPIs.summary.SummaryService;
import com.all3linesummary.naverAPIs.summary.dto.Document;
import com.all3linesummary.naverAPIs.summary.dto.Option;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
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
    private final NewsBodyRepository newsBodyRepository;
    private final NewsImageRepository newsImageRepository;
    private static final int REQUEST_NUMBER = 500; //6_000;
    private static final int DISPLAY_SIZE = 100;
    private static final int MAX_SUMMARY_SIZE = 10;//1_000;

    @Scheduled(cron ="0 0 12 1/1 * ?")
    @Transactional
    public void collectTodayNews(){
        List<SearchedNews> todaySearchedNewsList = requestTodayNews(REQUEST_NUMBER);
        List<SelectedNews> todaySelectedNewsList = selectNews.select(todaySearchedNewsList);
        Collections.sort(todaySelectedNewsList);
        List<News> todayNewsList = convertIntoNews(todaySelectedNewsList);
        saveNews(todayNewsList);
    }

    private void saveNews(List<News> newsList){
        for(News news : newsList){
            Long bodyId = saveNewsBody(news.getBody());
            saveNewsImage(news.getImageList(), bodyId);
        }
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

    private List<SearchedNews> requestTodayNews(int number){
        List<SearchedNews> newsGetList = new ArrayList<>(number);
        int cnt = (int) Math.ceil(number / 100);
        for(int i = 1; i <= cnt; i++){
            try {
                List<SearchedNews> searchResult = searchNewsService.get(DISPLAY_SIZE, i);
                newsGetList.addAll(searchResult);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        return newsGetList;
    }


    private List<News> convertIntoNews(List<SelectedNews> selectedNewsList){
        List<News> newsBodyList = new ArrayList<>();
        int summaryCnt = Math.min(selectedNewsList.size(), MAX_SUMMARY_SIZE);
        for(int i = 0; i < summaryCnt; i++){
            try {
                newsBodyList.add(getNews(selectedNewsList.get(i)));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return newsBodyList;
    }

    private News getNews(SelectedNews selectedNews)throws IOException{
        Element content = getContent(selectedNews.getURL());
        NewsBody body = getNewsBody(selectedNews, content);
        List<NewsImage> imageList = getNewsImage(content);
        return new News(body, imageList);
    }

    private List<NewsImage> getNewsImage(Element content){
        List<NewsImage> imageList = new ArrayList<>();
        Elements images = content.select("img");
        for (Element image : images) {
            String imageUrl = image.attr("data-src");
            NewsImage newsImage = new NewsImage(imageUrl);
            imageList.add(newsImage);
        }
        return imageList;
    }

    private NewsBody getNewsBody(SelectedNews selectedNews, Element content){
        String  summary = summaryNews(content, selectedNews.getTitle());
        NewsBody body = new NewsBody(selectedNews, summary);
        return body;
    }
    private Element getContent(String url) throws IOException {
        org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
        Element result = doc.selectFirst("div#dic_area");
        return result;
    }

    private String summaryNews(Element content, String title){
        String text = content.text();
        Document document = new Document(text, title);
        Option option = Option.getDefaultNews();
        String summary = summaryService.summary(document, option);
        return summary;
    }
}
