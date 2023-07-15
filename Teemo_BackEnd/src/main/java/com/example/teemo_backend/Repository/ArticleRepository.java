package com.example.teemo_backend.Repository;

import com.example.teemo_backend.Domain.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findAllByYearAndMonthAndWeek(int year,int month , int week);

}
