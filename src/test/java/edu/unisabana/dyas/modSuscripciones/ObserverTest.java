package edu.unisabana.dyas.modSuscripciones;

import edu.unisabana.dyas.modSuscripciones.adapter.PayPalAdapter;
import edu.unisabana.dyas.modSuscripciones.models.Subscription;
import edu.unisabana.dyas.modSuscripciones.models.User;
import edu.unisabana.dyas.modSuscripciones.observer.PaymentService;
import edu.unisabana.dyas.modSuscripciones.observer.SubscriptionManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ObserverTest {
    @Test
    public void testSubscriptionRenewalWithObserver() {
        // Crear una suscripción y un usuario
        Subscription premiumPlan = new Subscription("Premium Plan", 19.99);
        User user = new User("Carlos", true, true, LocalDate.now().minusMonths(1), premiumPlan); // Suscripción expirada

        // Crear una instancia de PayPal Adapter
        PayPalAdapter payPalAdapter = new PayPalAdapter();

        // Crear el servicio de pagos (Observer) y el gestor de suscripción (Subject)
        PaymentService paymentService = new PaymentService(user, payPalAdapter);
        SubscriptionManager subscriptionManager = new SubscriptionManager(user);

        // Agregar el servicio de pagos como observador del gestor de suscripciones
        subscriptionManager.addObserver(paymentService);

        // Simular la expiración de la suscripción y verificar la renovación
        subscriptionManager.checkSubscriptionStatus();

        // Verificamos que la suscripción se ha renovado por un mes más
        assertEquals(LocalDate.now().plusMonths(1), user.getSubscriptionEndDate());
    }

    @Test
    public void testSubscriptionNoRenewalWhenAutoRenewalDisabled() {
        // Crear una suscripción y un usuario con la autorrenovación desactivada
        Subscription basicPlan = new Subscription("Basic Plan", 9.99);
        User user = new User("Maria", true, false, LocalDate.now().minusMonths(1), basicPlan); // Suscripción expirada

        // Crear una instancia de PayPal Adapter
        PayPalAdapter payPalAdapter = new PayPalAdapter();

        // Crear el servicio de pagos (Observer) y el gestor de suscripción (Subject)
        PaymentService paymentService = new PaymentService(user, payPalAdapter);
        SubscriptionManager subscriptionManager = new SubscriptionManager(user);

        // Agregar el servicio de pagos como observador del gestor de suscripciones
        subscriptionManager.addObserver(paymentService);

        // Simular la expiración de la suscripción y verificar que no se renueva
        subscriptionManager.checkSubscriptionStatus();

        // Verificar que la suscripción no se ha renovado
        assertFalse(user.hasActiveSubscription());
    }
}
