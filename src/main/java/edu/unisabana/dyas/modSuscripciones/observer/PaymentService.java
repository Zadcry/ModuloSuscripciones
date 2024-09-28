package edu.unisabana.dyas.modSuscripciones.observer;

import edu.unisabana.dyas.modSuscripciones.adapter.PaymentGateway;
import edu.unisabana.dyas.modSuscripciones.models.User;

import java.time.LocalDate;

public class PaymentService implements SubscriptionObserver {
    private User user;
    private PaymentGateway paymentGateway;

    public PaymentService(User user, PaymentGateway paymentGateway) {
        this.user = user;
        this.paymentGateway = paymentGateway;
    }

    @Override
    public void update() {
        // Verificamos si la autorrenovación está habilitada
        if (user.isAutoRenew()) {
            System.out.println("Renovando la suscripción de " + user.getName());
            paymentGateway.processPayment(user.getSubscription().getPrice());
            user.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
            System.out.println("La suscripción ha sido renovada hasta " + user.getSubscriptionEndDate());
        } else {
            System.out.println("El usuario " + user.getName() + " ha desactivado la autorrenovación.");
            user.setHasActiveSubscription(false);
        }
    }
}