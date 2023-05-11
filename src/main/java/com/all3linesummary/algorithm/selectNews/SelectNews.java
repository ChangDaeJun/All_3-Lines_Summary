package com.all3linesummary.algorithm.selectNews;

import com.all3linesummary.naverAPIs.searchNews.dto.NewsGetResult;
import org.json.JSONArray;

import java.util.List;

public interface SelectNews {
    List<NewsGetResult> select(JSONArray collectResult);
}
