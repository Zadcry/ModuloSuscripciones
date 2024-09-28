package edu.unisabana.dyas.modSuscripciones.models;

import java.time.LocalDate;

public class User {
    private String name;
    private boolean activeSubscription;
    private boolean autoRenew;
    private LocalDate subscriptionEndDate;
    private Subscription subscription;

    public User(String name, boolean activeSubscription, boolean autoRenew, LocalDate subscriptionEndDate, Subscription subscription) {
        this.name = name;
        this.activeSubscription = activeSubscription;
        this.autoRenew = autoRenew;
        this.subscriptionEndDate = subscriptionEndDate;
        this.subscription = subscription;
    }

    public String getName() {
        return name;
    }

    public void setAutoRenewal(boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public boolean isAutoRenew() {
        return autoRenew;
    }

    public LocalDate getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(LocalDate subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public boolean hasActiveSubscription() {
        return activeSubscription;
    }

    public void setHasActiveSubscription(boolean activeSubscription) {
        this.activeSubscription = activeSubscription;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
