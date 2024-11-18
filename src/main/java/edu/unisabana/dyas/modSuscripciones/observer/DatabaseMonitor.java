package edu.unisabana.dyas.modSuscripciones.observer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DatabaseMonitor {
    private final Connection connection;
    private final List<DatabaseObserver> observers = new ArrayList<>();
    private int previousCount = -1; // Inicialmente desconocido

    public DatabaseMonitor(Connection connection) {
        this.connection = connection;
    }

    // Registrar un observer
    public void addObserver(DatabaseObserver observer) {
        observers.add(observer);
    }

    // Eliminar un observer
    public void removeObserver(DatabaseObserver observer) {
        observers.remove(observer);
    }

    // Notificar a los observers
    private void notifyObservers(int newCount) {
        for (DatabaseObserver observer : observers) {
            observer.onDataChange(newCount);
        }
    }

    // Método para iniciar la supervisión
    public void startMonitoring(long intervalMillis) {
        Timer timer = new Timer(true); // Timer en modo daemon
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    int currentCount = getRowCount();
                    if (currentCount != previousCount) {
                        notifyObservers(currentCount);
                        previousCount = currentCount;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, intervalMillis);
    }

    // Consulta para contar los registros en la tabla
    private int getRowCount() throws Exception {
        String countQuery = "SELECT COUNT(*) FROM Articles";
        try (PreparedStatement pstmt = connection.prepareStatement(countQuery);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
}

