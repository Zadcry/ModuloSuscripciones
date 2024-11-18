package com.example.project;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String subscriptionStatus; // Campo opcional para los tests, si es necesario.

    // Constructor vacío
    public User() {}

    // Constructor con todos los campos
    public User(String id, String name, String email, String password, String subscriptionStatus) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.subscriptionStatus = subscriptionStatus;
    }

    // Constructor sin subscriptionStatus
    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }
}
