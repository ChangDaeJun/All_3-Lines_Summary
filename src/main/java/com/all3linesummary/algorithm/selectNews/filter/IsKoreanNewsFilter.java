package com.all3linesummary.algorithm.selectNews.filter;

import com.all3linesummary.naverAPIs.searchNews.dto.SearchedNews;
import com.all3linesummary.util.filter.Filter;

public class IsKoreanNewsFilter implements Filter<SearchedNews> {

    @Override
    public int checkPoint(SearchedNews news) {
        int koreanCnt = countKorean(news.getTitle());
        int passNumber = (int) (news.getTitleLength() * 0.6);
        if(koreanCnt > passNumber) return 0;
        return FAIL_DIGIT;
    }

    private int countKorean(String title){
        int cnt = 0;
        for(char c : title.toCharArray()){
            if (isKorean(c)) cnt++;
        }
        return cnt;
    }

    // 한글 유니코드 범위: 가(0xAC00) ~ 힣(0xD7A3)
    private boolean isKorean(char c) {
        if (c >= '\uAC00' && c <= '\uD7A3') return true;
        return false;
    }
}
