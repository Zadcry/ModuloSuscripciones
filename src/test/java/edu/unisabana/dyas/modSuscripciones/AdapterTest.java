package edu.unisabana.dyas.modSuscripciones;

import edu.unisabana.dyas.modSuscripciones.adapter.PayPalAdapter;
import edu.unisabana.dyas.modSuscripciones.adapter.PaymentGateway;
import edu.unisabana.dyas.modSuscripciones.adapter.StripeAdapter;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AdapterTest {

    @Test
    public void testPayPalPaymentProcessing() {
        PaymentGateway payPalGateway = new PayPalAdapter();

        // Simulamos un pago de $19.99 con PayPal
        payPalGateway.processPayment(19.99);
        assertNotNull(payPalGateway);  // Solo verificamos que el proceso no sea nulo
    }

    @Test
    public void testStripePaymentProcessing() {
        PaymentGateway stripeGateway = new StripeAdapter();

        // Simulamos un pago de $29.99 con Stripe
        stripeGateway.processPayment(29.99);
        assertNotNull(stripeGateway);  // Solo verificamos que el proceso no sea nulo
    }
}
