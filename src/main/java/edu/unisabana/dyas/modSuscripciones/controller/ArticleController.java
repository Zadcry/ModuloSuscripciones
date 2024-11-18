package edu.unisabana.dyas.modSuscripciones.controller;

import edu.unisabana.dyas.modSuscripciones.adapter.ArticleAdapter;
import edu.unisabana.dyas.modSuscripciones.models.Article;
import edu.unisabana.dyas.modSuscripciones.models.ArticleDTO;
import edu.unisabana.dyas.modSuscripciones.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleAdapter articleAdapter;

    public ArticleController(ArticleAdapter articleAdapter) {
        this.articleAdapter = articleAdapter;
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articleAdapter.getAllArticles();
    }
}

