package com.all3linesummary.news;

import com.all3linesummary.domain.News;
import com.all3linesummary.news.repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CollectTodayNewsScheduleTest {

    @Autowired
    private CollectTodayNewsSchedule collectTodayNewsSchedule;
    @Autowired
    private NewsRepository newsRepository;

    @Test
    void collectTodayNews() {
        collectTodayNewsSchedule.collectTodayNews();
        List<News> list = newsRepository.findAll();
    }
}