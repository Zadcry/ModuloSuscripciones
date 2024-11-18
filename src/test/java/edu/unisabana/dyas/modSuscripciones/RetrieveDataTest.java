package edu.unisabana.dyas.modSuscripciones;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RetrieveDataTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        // Configurar conexión a la base de datos usando DatabaseConnection
        connection = DatabaseConnection.getConnection();

        // Crear la tabla Articles
        String createTableSQL = "CREATE TABLE IF NOT EXISTS ArticlesTest1 (id INT AUTO_INCREMENT PRIMARY KEY, Title VARCHAR(255), Synopsis VARCHAR(500), Cover MEDIUMBLOB, ContentImages1 MEDIUMBLOB, ContentImages2 MEDIUMBLOB, ContentImages3 MEDIUMBLOB, Content VARCHAR(5000));";

        try (PreparedStatement stmt = connection.prepareStatement(createTableSQL)) {
            stmt.execute();
        }

        // Insertar datos de prueba
        String insertSQL = "INSERT INTO ArticlesTest1 (Title, Synopsis, Cover, ContentImages1, ContentImages2, ContentImages3, Content) VALUES ('Test Article', 'This is a synopsis.', NULL, NULL, NULL, NULL, 'This is content.');";
        try (PreparedStatement stmt = connection.prepareStatement(insertSQL)) {
            stmt.executeUpdate();
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (connection != null) {
            // Limpiar la tabla después de cada prueba
            try (PreparedStatement stmt = connection.prepareStatement("DROP TABLE IF EXISTS ArticlesTest1")) {
                stmt.execute();
            }
            connection.close();
        }
    }

    @Test
    public void testRetrieveTitleById() throws Exception {
        // Recuperar el título del artículo con ID 1
        String title = RetrieveData.retrieveTitleById(connection,"ArticlesTest1", 1);

        // Validar que el título es correcto
        assertEquals("Test Article", title, "El título debe coincidir con el valor esperado.");
    }

    @Test
    public void testRetrieveTitleByIdWithInvalidId() throws Exception {
        // Intentar recuperar un artículo que no existe
        String title = RetrieveData.retrieveTitleById(connection,"ArticlesTest1", 999);

        // Validar que el resultado es null
        assertNull(title, "Debe devolver null si el ID no existe.");
    }
}

