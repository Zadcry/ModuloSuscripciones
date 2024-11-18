package edu.unisabana.dyas.modSuscripciones.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;

@Entity
@Getter
@Setter
public class Article {
    @Id
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String synopsis;

    @Column(nullable = false, columnDefinition = "MEDIUMBLOB")
    private byte[] cover;

    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] contentImages1;

    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] contentImages2;

    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] contentImages3;

    @Column(columnDefinition = "TEXT")
    private String content;

    // Getters y Setters
}

