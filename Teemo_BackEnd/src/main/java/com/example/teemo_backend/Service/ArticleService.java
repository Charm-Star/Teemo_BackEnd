package com.example.teemo_backend.Service;

import com.example.teemo_backend.Domain.Entity.Article;
import com.example.teemo_backend.Repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ArticleService {
    final String url ="https://blog.toss.im";
    Connection conn = Jsoup.connect(url);
    private final ArticleRepository articleRepository;
    public void crawlingArticle() {
        final String url = "https://blog.toss.im";
        Connection conn = Jsoup.connect(url);
        ArrayList<Article> articles = new ArrayList<>();
        try {

            StringBuilder sb = new StringBuilder();
            Document document = conn.get();
            Elements articleList = document.select("ul.css-lxkglh li a");
            LocalDate currentDate = LocalDate.now();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int year = currentDate.getYear();
            int month = currentDate.getMonthValue();
            int weekNumber = currentDate.get(weekFields.weekOfMonth());


            for (Element elements : articleList) {
                Article art = new Article();
                art.setUrl(elements.attr("href")); // 주소값 가지고 오기
                art.setTitle(elements.getElementsByClass("css-wbmll4").text());
                art.setArtContent(elements.getElementsByClass("css-r87obc").text());
                art.setImgUrl(elements.select("span img").attr("src"));
                art.setYear(year);
                art.setMonth(month);
                art.setWeek(weekNumber);
                articles.add(art);

            }

        } catch (IOException ignored) {

        }

        articleRepository.saveAll(articles);


    }
    public List<Article> getArticles(int year,int month , int week){


        List<Article> articles = articleRepository.findAllByYearAndMonthAndWeek(year,month,week);


        return articles;
    }


}
