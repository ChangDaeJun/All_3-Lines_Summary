package com.all3linesummary.news;

import com.all3linesummary.news.dto.NewsResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NewsController {
    private final NewsService newsService;
    private final CollectTodayNewsSchedule collectTodayNewsSchedule;

    @GetMapping("/news/list")
    @ResponseBody
    public NewsResponse getNewsList(@PageableDefault(size = 10)Pageable pageable){
        NewsResponse response = newsService.getNews(pageable);
        return response;
    }

    @GetMapping("/data")
    public String test(){
        collectTodayNewsSchedule.collectTodayNews();
        return "success";
    }
}
