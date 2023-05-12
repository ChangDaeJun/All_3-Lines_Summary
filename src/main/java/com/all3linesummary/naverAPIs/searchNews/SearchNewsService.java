package com.all3linesummary.naverAPIs.searchNews;

import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;

import java.util.List;

public interface SearchNewsService {
    List<SearchedNews> get(int display, int start);
}
