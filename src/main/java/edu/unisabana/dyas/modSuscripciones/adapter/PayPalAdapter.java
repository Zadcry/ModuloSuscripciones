package edu.unisabana.dyas.modSuscripciones.adapter;

public class PayPalAdapter implements PaymentGateway {
    private PayPal payPal;

    public PayPalAdapter() {
        this.payPal = new PayPal();
    }

    @Override
    public void processPayment(double amount) {
        payPal.pay(amount);
    }
}
