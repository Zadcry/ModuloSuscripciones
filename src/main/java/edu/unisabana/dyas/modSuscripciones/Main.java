package edu.unisabana.dyas.modSuscripciones;

import edu.unisabana.dyas.modSuscripciones.adapter.PayPalAdapter;
import edu.unisabana.dyas.modSuscripciones.adapter.PaymentGateway;
import edu.unisabana.dyas.modSuscripciones.models.Subscription;
import edu.unisabana.dyas.modSuscripciones.models.User;
import edu.unisabana.dyas.modSuscripciones.observer.PaymentService;
import edu.unisabana.dyas.modSuscripciones.observer.SubscriptionManager;
import edu.unisabana.dyas.modSuscripciones.proxy.ContentService;
import edu.unisabana.dyas.modSuscripciones.proxy.SubscriptionProxy;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // 1. Crear una suscripción
        Subscription premiumPlan = new Subscription("Premium Plan", 19.99);

        // 2. Crear un usuario con la suscripción expirada
        User user = new User("Carlos", true, true, LocalDate.now().minusMonths(1), premiumPlan);

        // 3. Crear una instancia del adaptador de PayPal (simulación de pasarela de pagos)
        PayPalAdapter payPalAdapter = new PayPalAdapter();

        // 4. Crear el servicio de pagos (Observer)
        PaymentService paymentService = new PaymentService(user, payPalAdapter);

        // 5. Crear el gestor de suscripción (Subject)
        SubscriptionManager subscriptionManager = new SubscriptionManager(user);

        // 6. Agregar el servicio de pagos como observador del gestor de suscripción
        subscriptionManager.addObserver(paymentService);

        // 7. Simular la expiración de la suscripción y verificar si se renueva
        System.out.println("Verificando estado de suscripción para el usuario: " + user.getName());
        subscriptionManager.checkSubscriptionStatus();

        // 8. Verificar el estado de la suscripción después de intentar renovarla
        if (LocalDate.now().isBefore(user.getSubscriptionEndDate())) {
            System.out.println("La suscripción se ha renovado correctamente. Nueva fecha de expiración: " + user.getSubscriptionEndDate());
        } else {
            System.out.println("La suscripción no fue renovada.");
        }

        // 9. Desactivar la autorrenovación
        user.setAutoRenewal(false);

        // 10. Simular de nuevo la expiración de la suscripción
        System.out.println("\nEl usuario ha desactivado la autorrenovación.");
        subscriptionManager.checkSubscriptionStatus();
    }
}
