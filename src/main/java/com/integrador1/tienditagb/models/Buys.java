package com.integrador1.tienditagb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Buys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int product;
    private int provider;
    private int user;
    private int purchasedQuantity;
    private double amount;

    public Buys() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct() {
        return this.product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getProvider() {
        return this.provider;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }

    public int getUser() {
        return this.user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getPurchasedQuantity() {
        return this.purchasedQuantity;
    }

    public void setPurchasedQuantity(int purchasedQuantity) {
        this.purchasedQuantity = purchasedQuantity;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
