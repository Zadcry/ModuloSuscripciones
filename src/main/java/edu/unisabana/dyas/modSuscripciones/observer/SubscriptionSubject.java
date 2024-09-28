package edu.unisabana.dyas.modSuscripciones.observer;

public interface SubscriptionSubject {
    void addObserver(SubscriptionObserver observer);
    void removeObserver(SubscriptionObserver observer);
    void notifyObservers();
}
