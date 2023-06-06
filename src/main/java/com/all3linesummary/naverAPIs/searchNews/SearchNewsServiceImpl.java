package com.all3linesummary.naverAPIs.searchNews;

import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchNewsServiceImpl implements SearchNewsService {
    @Value("${naver.api.search.url}")
    private String url;
    @Value("${naver.api.search.id}")
    private String id;
    @Value("${naver.api.search.secret}")
    private String secret;
    @Override
    public List<SearchedNews> get(int display, int start) {
        String parameter = getParameter("뉴스", display, start, "date");

        JSONObject result = GET(url, "", parameter, id, secret);

        JSONArray newsArray = result.getJSONArray("items");
        return changeJsonToSearchedNews(newsArray);
    }
    private List<SearchedNews> changeJsonToSearchedNews(JSONArray array){
        List<SearchedNews> list = new ArrayList<>();
        for(Object item : array){
            SearchedNews news = new SearchedNews((JSONObject) item);
            list.add(news);
        }
        return list;
    }
    private String getParameter(String query, int display, int start, String sort){
        String parameter;
        try {
            parameter = "?query=" + URLEncoder.encode(query, "UTF-8") +
                    "&display="+display +
                    "&start=" +start +
                    "&sort=" + sort;
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return "";
        }

        return parameter;
    }

    private JSONObject GET(String baseURL, String api_name, String parameter, String id, String secret){
        JSONObject responseJson = null;

        try {
            URL url = new URL(baseURL + api_name + parameter);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Naver-Client-Id", id);
            conn.setRequestProperty("X-Naver-Client-Secret", secret);

            int responseCode = conn.getResponseCode();
            String message = conn.getResponseMessage();
            if(responseCode != 200){
                System.out.println(message);
                System.out.println(responseCode + " error ");
            }
            else{
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                while(br.ready()){
                    sb.append(br.readLine());
                }
                responseJson = new JSONObject(sb.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e){

        }

        return responseJson;
    }

    public List<SearchedNews> requestTodayNews(int size, int number){
        List<SearchedNews> newsGetList = new ArrayList<>(number);

        for(int cnt = 1; cnt <= number; cnt += size){
            try {
                List<SearchedNews> searchResult = get(size, cnt);
                newsGetList.addAll(searchResult);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        return newsGetList;
    }
}
