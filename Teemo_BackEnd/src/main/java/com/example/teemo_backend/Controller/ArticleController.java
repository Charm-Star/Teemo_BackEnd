package com.example.teemo_backend.Controller;


import com.example.teemo_backend.Domain.Entity.Article;
import com.example.teemo_backend.Service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/article")
public class ArticleController {
    private final ArticleService articleService;


    @Scheduled(cron = "0 0 0 ? * SUN", zone = "Asia/Seoul")
    @GetMapping(value = "/crawling-article")
    public void crawlingWeeklyArticle(){

        articleService.crawlingArticle();

    }

    @GetMapping(value = "/weekly-article")
    public List<Article> getWeeklyArticle(@RequestParam int year,@RequestParam int month,@RequestParam int week){

        return articleService.getArticles(year,month,week);

    }



}
