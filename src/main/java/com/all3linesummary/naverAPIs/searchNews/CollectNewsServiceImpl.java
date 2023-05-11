package com.all3linesummary.naverAPIs.searchNews;

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

@Component
public class CollectNewsServiceImpl implements CollectNewsService {
    @Value("${naver.api.searchAPI}")
    private String url;
    @Value("${naver.api.id}")
    private String id;
    @Value("${naver.api.secret}")
    private String secret;
    @Override
    public JSONArray get(int display, int start) {
        String parameter = getParameter("뉴스", display, start, "date");
        JSONObject result = GET(url, "", parameter, id, secret);
        return result.getJSONArray("items");
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
            if(responseCode != 200){
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
}
