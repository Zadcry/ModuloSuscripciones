package edu.unisabana.dyas.modSuscripciones;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class FirebaseConnectionTest {

    public static void main(String[] args) {
        // Inicializar Firebase
        FirebaseInitializer.initializeFirebase();

        // Obtener referencia a Realtime Database
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("test_connection");

        // Escribir datos en la base de datos
        ref.setValueAsync("¡Conexión exitosa!");

        // Leer los datos para confirmar la conexión
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("Datos en la base de datos: " + snapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Error al leer los datos: " + error.getMessage());
            }
        });
    }
}
