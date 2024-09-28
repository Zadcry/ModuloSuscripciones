package edu.unisabana.dyas.modSuscripciones;

import edu.unisabana.dyas.modSuscripciones.models.User;
import edu.unisabana.dyas.modSuscripciones.proxy.ContentService;
import edu.unisabana.dyas.modSuscripciones.proxy.SubscriptionProxy;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class ProxyTest {

    @Test
    public void testAccessWithActiveSubscription() {
        User user = new User("Maria", true, false, null,null);  // Usuario con suscripción activa
        ContentService proxy = new SubscriptionProxy();

        // Simulamos la salida esperada cuando se accede al contenido
        proxy.accessContent(user);  // Debería permitir acceso
        assertTrue(user.hasActiveSubscription());
    }

    @Test
    public void testAccessWithoutActiveSubscription() {
        User user = new User("Jose", false, false, null,null);  // Usuario sin suscripción activa
        ContentService proxy = new SubscriptionProxy();

        // Simulamos la salida esperada cuando se deniega el acceso al contenido
        proxy.accessContent(user);  // No debería permitir acceso
        assertFalse(user.hasActiveSubscription());
    }
}
