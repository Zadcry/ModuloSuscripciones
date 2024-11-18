package edu.unisabana.dyas.modSuscripciones.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDTO {
    private String title;
    private String synopsis;
    private String cover;
    private String contentImages1;
    private String contentImages2;
    private String contentImages3;
    private String content;

    public ArticleDTO(String title, String synopsis, String cover, String contentImages1, 
                      String contentImages2, String contentImages3, String content) {
        this.title = title;
        this.synopsis = synopsis;
        this.cover = cover;
        this.contentImages1 = contentImages1;
        this.contentImages2 = contentImages2;
        this.contentImages3 = contentImages3;
        this.content = content;
    }

    // Getters y Setters
}
