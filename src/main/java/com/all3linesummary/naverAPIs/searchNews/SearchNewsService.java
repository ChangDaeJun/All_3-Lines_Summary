package com.all3linesummary.naverAPIs.searchNews;

import com.all3linesummary.naverAPIs.searchNews.dto.NewsGetResult;
import org.json.JSONArray;

import java.util.List;

public interface SearchNewsService {
    List<NewsGetResult> get(int display, int start);
}
