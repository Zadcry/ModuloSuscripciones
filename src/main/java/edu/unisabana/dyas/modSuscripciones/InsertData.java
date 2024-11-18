package edu.unisabana.dyas.modSuscripciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.File;
import java.io.FileInputStream;

public class InsertData {
    public static void main(String[] args) {
        // Consulta SQL para insertar datos
        String sql = "INSERT INTO Articles (Title, Synopsis, Cover, ContentImages1, ContentImages2, ContentImages3, Content) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establecer valores para las columnas
            pstmt.setString(1, "Deadpool & Wolverine"); // Title
            pstmt.setString(2, "In this multiverse story Deadpool is approached by a man named Paradox who intends on vaporizing his entire universe because apparently it's not supposed to go on living without the now deceased hero Wolverine. Deadpool kidnaps another version of Wolverine to replace but this only results in Paradox banishing both Deadpool and Wolverine."); // Synopsis

            // Leer archivos binarios (imágenes y contenido)
            pstmt.setBlob(3, new FileInputStream(new File("Contents\\Deadpool3\\Cover.jpg")));       // Cover
            pstmt.setBlob(4, new FileInputStream(new File("Contents\\Deadpool3\\ContentImages1.jpg")));   // ContentImages1
            pstmt.setBlob(5, new FileInputStream(new File("Contents\\Deadpool3\\ContentImages2.jpg")));   // ContentImages2
            pstmt.setBlob(6, new FileInputStream(new File("Contents\\Deadpool3\\ContentImages3.jpg")));   // ContentImages3
            pstmt.setString(7, "https://www.youtube.com/watch?v=pn5fdK61o9c");

            // Ejecutar la consulta
            int filasInsertadas = pstmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("¡Inserción exitosa!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
