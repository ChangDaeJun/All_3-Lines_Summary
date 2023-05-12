package com.all3linesummary.news;

import com.all3linesummary.domain.NewsBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CollectTodayNewsScheduleTest {

    @Autowired
    private CollectTodayNewsSchedule collectTodayNewsSchedule;
    @Autowired
    private NewsBodyRepository newsBodyRepository;

    @Test
    void collectTodayNews() {
        collectTodayNewsSchedule.collectTodayNews();
        List<NewsBody> list = newsBodyRepository.findAll();
        for(NewsBody news : list){
            System.out.println(news.toString());
        }
    }
}