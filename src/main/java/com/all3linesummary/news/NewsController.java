package com.all3linesummary.news;

import com.all3linesummary.domain.News;
import com.all3linesummary.news.dto.NewsResponse;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class NewsController {
    private final NewsReadService newsReadService;
    private final CollectTodayNewsSchedule collectTodayNewsSchedule;

    @GetMapping("/news/list")
    @ResponseBody
    public NewsResponse getNewsList(@PageableDefault(size = 10)Pageable pageable){
        System.out.println("@@@@@@@@@"+pageable.getPageNumber());
        NewsResponse response = newsReadService.getNewsResponse(pageable);
        return response;
    }

    @GetMapping("/data")
    public String test(){
        collectTodayNewsSchedule.collectTodayNews();
        return "success";
    }
}
