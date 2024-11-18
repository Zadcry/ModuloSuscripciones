package edu.unisabana.dyas.modSuscripciones;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsertDataTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        // Obtener la conexión a la base de datos usando tu clase DatabaseConnection
        connection = DatabaseConnection.getConnection();

        // Crear la tabla si no existe
        String createTableSQL = "CREATE TABLE IF NOT EXISTS ArticlesTest (id INT AUTO_INCREMENT PRIMARY KEY, Title VARCHAR(255), Synopsis VARCHAR(500), Cover MEDIUMBLOB, ContentImages1 MEDIUMBLOB, ContentImages2 MEDIUMBLOB, ContentImages3 MEDIUMBLOB, Content VARCHAR(5000));";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (connection != null) {
            // Limpiar los datos de la tabla después de cada prueba
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("DELETE FROM ArticlesTest");
            }
            connection.close();
        }
    }

    @Test
    public void testInsertData() throws Exception {
        // SQL para insertar datos
        String insertSQL = "INSERT INTO ArticlesTest (Title, Synopsis, Cover, ContentImages1, ContentImages2, ContentImages3, Content) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {

            pstmt.setString(1, "Deadpool & Wolverine"); // Title
            pstmt.setString(2, "In this multiverse story Deadpool is approached by a man named Paradox who intends on vaporizing his entire universe because apparently it's not supposed to go on living without the now deceased hero Wolverine. Deadpool kidnaps another version of Wolverine to replace but this only results in Paradox banishing both Deadpool and Wolverine."); // Synopsis

            // Leer archivos binarios (imágenes y contenido)
            pstmt.setBlob(3, new FileInputStream(new File("Contents\\Deadpool3\\Cover.jpg")));       // Cover
            pstmt.setBlob(4, new FileInputStream(new File("Contents\\Deadpool3\\ContentImages1.jpg")));   // ContentImages1
            pstmt.setBlob(5, new FileInputStream(new File("Contents\\Deadpool3\\ContentImages2.jpg")));   // ContentImages2
            pstmt.setBlob(6, new FileInputStream(new File("Contents\\Deadpool3\\ContentImages3.jpg")));   // ContentImages3
            pstmt.setString(7, "https://www.youtube.com/watch?v=pn5fdK61o9c");

            int rowsInserted = pstmt.executeUpdate();
            assertEquals(1, rowsInserted, "Se debe insertar un registro");
        }

        // Verificar que los datos se insertaron correctamente
        String selectSQL = "SELECT COUNT(*) AS total FROM ArticlesTest";
        try (PreparedStatement pstmt = connection.prepareStatement(selectSQL);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                int total = rs.getInt("total");
                assertEquals(1, total, "Debe haber un registro en la tabla");
            }
        }
    }
}

