package edu.unisabana.dyas.modSuscripciones.observer;

import edu.unisabana.dyas.modSuscripciones.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionManager implements SubscriptionSubject {
    private List<SubscriptionObserver> observers;
    private User user;

    public SubscriptionManager(User user) {
        this.observers = new ArrayList<>();
        this.user = user;
    }

    @Override
    public void addObserver(SubscriptionObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(SubscriptionObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (SubscriptionObserver observer : observers) {
            observer.update(); // Notificamos a todos los observadores
        }
    }

    public void checkSubscriptionStatus() {
        // Verificamos si la suscripción está cerca de expirar
        if (LocalDate.now().isAfter(user.getSubscriptionEndDate())) {
            System.out.println("La suscripción de " + user.getName() + " ha expirado.");
            notifyObservers(); // Notificamos a los observadores que la suscripción expiró
        }
    }
}