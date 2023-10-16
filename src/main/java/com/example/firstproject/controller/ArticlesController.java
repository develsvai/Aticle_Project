package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticlesForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller //이 클래스가 Controller 임을 선언

public class ArticlesController {
    @Autowired
    private ArticleRepository articleRepository;

    //아디클 입력 페이지
    @GetMapping("/articles/new")
    public String newArticlesForm() {
        return "articles/new";
    }

    //아티클 생성
    @PostMapping("/articles/create")
    public String createArticles(ArticlesForm form) {
        log.info(form.toString());

        Article article = form.toEntity();
        log.info(article.toString());

        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    //아티클 상세 페이지
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);

        return "articles/show";
    }

    //현재 까지 생성한 아티클 전부 불러오기

    @GetMapping("/articles")
    public String index(Model model) {
        List<Article> articleEntityList = articleRepository.findAll();

        model.addAttribute("articleList", articleEntityList);

        return "articles/index";
    }
    //db 데이터 수정

    @GetMapping("/articles/{id}/edit")
    public String Edie(@PathVariable Long id , Model model){
        log.info("id ="+id);

        Article articledata = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articledata);

        return "articles/edit";
    }
    //db 데이터 업데이트

    @PostMapping("/articles/update")
    public String update(ArticlesForm form){
        log.info(form.toString());

        Article article = form.toEntity();
        Article saved = articleRepository.save(article);

        return "redirect:/articles/" + saved.getId();
    }


    //db 데이터 삭제
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        Article Target = articleRepository.findById(id).orElse(null);

        if (Target != null) {
            articleRepository.delete(Target);
            rttr.addFlashAttribute("msg", "삭제 완료");
        }
        return "redirect:/articles";
    }


}


