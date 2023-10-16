package com.example.firstproject.api;

import com.example.firstproject.dto.ArticlesForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class FirstApiController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/api/hello")
    public String hello(){
        return "hello world";
    }

    @GetMapping("/api/articles")
    public List<Article> index () {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show  (@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }



    @PostMapping("/api/articles/create")
    public Article create (@RequestBody ArticlesForm dto) {
        Article article = dto.toEntity();

        return articleRepository.save(article);
    }
}

