package edu.unisabana.dyas.modSuscripciones.service;

import edu.unisabana.dyas.modSuscripciones.models.Article;
import edu.unisabana.dyas.modSuscripciones.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
