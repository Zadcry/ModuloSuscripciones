package edu.unisabana.dyas.modSuscripciones.adapter;

class Stripe {
    public void charge(double amount) {
        System.out.println("Procesando pago de $" + amount + " mediante Stripe.");
    }
}
