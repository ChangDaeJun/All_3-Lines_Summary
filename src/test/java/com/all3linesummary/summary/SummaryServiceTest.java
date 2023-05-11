package com.all3linesummary.summary;

import com.all3linesummary.summary.dto.Document;
import com.all3linesummary.summary.dto.Option;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SummaryServiceTest {

    @Autowired
    private SummaryService summaryService;

    @Test
    public void 기본_테스트(){
        String content = "노조는 ‘파업’, ‘강성’, ‘귀족’... 기업은 ‘투자’, ‘일자리’, ‘부담’\n" +
                "\n" +
                "노동조합을 다룬 사설 3,377건에서 가장 많이 언급된 단어는 ‘노조’로 8,309번 나왔다. 정부(8,091회)와 기업(4,932회)이 뒤를 이었다. 분석 범위는 노동조합을 다룬 사설들이었지만, 노동(3,615회, 6위)보다 경제(4,653회, 4위)나 일자리(3,777회, 5위)가 더 많이 언급됐다. 노조와 직접 관계가 있는 키워드 중에서는 임금(3,321회, 8위)과 파업(2,613회, 12위)가 자주 나왔다.\n" +
                "\n" +
                "노동조합을 다룬 한국 언론의 사설들은 대체로 ‘노조가 임금 때문에 파업을 하면, 경제와 일자리 창출에 도움이 되지 않는다’는 논조를 보였다. 이러한 논조는 서로 인접해서 나오는 단어들을 연결망으로 분석했을 때 더 뚜렷하게 드러났다.\n" +
                "\n" +
                "‘노조’는 ‘파업’과 연달아 나오는 경우가 375건으로 가장 많았다. ‘노조’는 ‘강성’, ‘귀족’, ‘기득권’, ‘불법’ 등의 부정적인 단어로 묘사되는 경우가 많았다. 반면 언론사 사설에서 기업을 묘사하는 단어들은 노조를 표현하는 단어들과 뚜렷하게 대조됐다. ‘기업’은 ‘투자’와 연달아 나오는 경우가 311건으로 가장 많았다. 언론사 사설에서 기업은 ‘투자’하고, ‘고용’하고, ‘일자리’를 창출하는 주체로 묘사됐다. 또 기업은 ‘경쟁력’을 지키기 위해 ‘정부’가 ‘부담’을 덜어줘야 하는 대상으로 그려지기도 했다.";
        String title = "'불법', '강성', '기득', '귀족'...... 노동조합 그리는 언론사들의 시선";
        Document document1 = new Document(content, title);
        Option option1 = new Option("ko", "news", 0, 3);

        String summary = summaryService.summary(document1, option1);
        System.out.println(summary);
    }

}