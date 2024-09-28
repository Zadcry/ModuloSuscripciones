package edu.unisabana.dyas.modSuscripciones.adapter;

public class StripeAdapter implements PaymentGateway {
    private Stripe stripe;

    public StripeAdapter() {
        this.stripe = new Stripe();
    }

    @Override
    public void processPayment(double amount) {
        stripe.charge(amount);
    }
}
