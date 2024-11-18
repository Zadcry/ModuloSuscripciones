package edu.unisabana.dyas.modSuscripciones;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {

    public static void initializeFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("dyas-grupoagz-firebase-adminsdk-kca0x-04b145f149.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://dyas-grupoagz-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println("Firebase inicializado correctamente");

        } catch (IOException e) {
            System.err.println("Error al inicializar Firebase: " + e.getMessage());
        }
    }
}
