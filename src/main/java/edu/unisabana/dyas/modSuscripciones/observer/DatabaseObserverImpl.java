package edu.unisabana.dyas.modSuscripciones.observer;

public class DatabaseObserverImpl implements DatabaseObserver {
    @Override
    public void onDataChange(int newCount) {
        System.out.println("¡El número de registros ha cambiado! Nuevo total: " + newCount);
    }
}

