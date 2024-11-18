package edu.unisabana.dyas.modSuscripciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveData {
    public static String retrieveTitleById(Connection connection, String table, int id) throws Exception {
        String query = "SELECT Title FROM "+table+" WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Title");
                }
            }
        }
        return null;
    }
    public static void main(String[] args) {
        String query = "SELECT Title, Synopsis, Cover FROM Articles"; // Ajusta la consulta según tu tabla

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // Iterar sobre los resultados
            while (rs.next()) {
                String title = rs.getString("Title");
                String synopsis = rs.getString("Synopsis");

                System.out.println("Title: " + title);
                System.out.println("Synopsis: " + synopsis);
                System.out.println("----------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

