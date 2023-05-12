package com.all3linesummary.algorithm.selectNews;

import com.all3linesummary.algorithm.selectNews.dto.SelectedNews;
import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;

import java.util.List;

public interface SelectNews {
    List<SelectedNews> select(List<SearchedNews> news);
}
