package com.all3linesummary.summary;

import com.all3linesummary.summary.dto.Document;
import com.all3linesummary.summary.dto.Option;
import com.all3linesummary.summary.dto.SummaryRequest;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class SummaryServiceImpl implements SummaryService {

    @Value("${naver.api.summary.url}")
    private String API;
    @Value("${naver.api.summary.id}")
    private String id;
    @Value("${naver.api.summary.secret}")
    private String secret;

    @Override
    public String summary(Document document, Option option) {
        SummaryRequest request = new SummaryRequest(document, option);
        JSONObject result = POST(this.API, this.id, this.secret, request);
        return result.getString("summary");
    }

    private JSONObject POST(String baseURL, String id, String secret, SummaryRequest body){
        JSONObject responseJson = null;
        Gson gson = new Gson();
        String pa = gson.toJson(body);
        try {
            URL url = new URL(baseURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", id);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", secret);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            bw.write(pa);
            bw.flush();
            bw.close();

            int responseCode = conn.getResponseCode();
            if(responseCode != 200){
                System.out.println(conn.getResponseMessage());
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
