package edu.unisabana.dyas.modSuscripciones.proxy;

import edu.unisabana.dyas.modSuscripciones.models.User;

public class SubscriptionProxy implements ContentService {
    private RealContentService realContentService;

    public SubscriptionProxy() {
        this.realContentService = new RealContentService();
    }

    @Override
    public void accessContent(User user) {
        if (user.hasActiveSubscription()) {
            realContentService.accessContent(user);
        } else {
            System.out.println("Acceso denegado. No tiene una suscripción activa.");
        }
    }
}
