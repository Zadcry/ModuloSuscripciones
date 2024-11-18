package edu.unisabana.dyas.modSuscripciones.repository;

import edu.unisabana.dyas.modSuscripciones.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
