package com.all3linesummary.naverAPIs.searchNews;

import org.json.JSONArray;

public interface CollectNewsService {
    JSONArray get(int display, int start);
}
