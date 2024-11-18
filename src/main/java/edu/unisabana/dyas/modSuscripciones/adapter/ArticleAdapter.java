package edu.unisabana.dyas.modSuscripciones.adapter;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.unisabana.dyas.modSuscripciones.DatabaseConnection;
import edu.unisabana.dyas.modSuscripciones.models.Article;

@Component
public class ArticleAdapter {
    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT Title, Synopsis, Cover, ContentImages1, ContentImages2, ContentImages3, Content FROM Articles";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Article article = new Article();

                article.setTitle(rs.getString("Title"));
                article.setSynopsis(rs.getString("Synopsis"));

                // Convertir cada BLOB a Base64
                article.setCover(rs.getBinaryStream("Cover").readAllBytes());
                article.setContentImages1(rs.getBinaryStream("ContentImages1").readAllBytes());
                article.setContentImages2(rs.getBinaryStream("ContentImages2").readAllBytes());
                article.setContentImages3(rs.getBinaryStream("ContentImages3").readAllBytes());
                article.setContent(rs.getString("Content"));

                System.out.println("Consulta ejecutada: SELECT * FROM Articles");
                System.out.println("¿Hay registros?: " + rs.isBeforeFirst());

                articles.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return articles;
    }

    private String convertBlobToBase64(InputStream blobStream) {
        try {
            byte[] bytes = blobStream.readAllBytes();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
