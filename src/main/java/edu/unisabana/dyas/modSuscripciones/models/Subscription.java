package edu.unisabana.dyas.modSuscripciones.models;

public class Subscription {
    private String name;
    private double price;

    public Subscription(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
