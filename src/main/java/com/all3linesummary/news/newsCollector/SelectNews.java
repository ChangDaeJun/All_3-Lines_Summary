package com.all3linesummary.news.newsCollector;

import com.all3linesummary.news.dto.NewsDTO;
import org.json.JSONArray;

import java.util.List;

public interface SelectNews {
    List<NewsDTO> select(JSONArray collectResult);
}
