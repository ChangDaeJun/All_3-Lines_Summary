package com.all3linesummary.news.newsCollector;

import org.json.JSONArray;

import java.io.IOException;

public interface CollectNews {
    JSONArray get(int display, int start);
}
