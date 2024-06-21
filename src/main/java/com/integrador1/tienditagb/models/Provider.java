package com.integrador1.tienditagb.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(columnDefinition = "VARCHAR(100)")
    private String address;

    @Column(columnDefinition = "VARCHAR(50)")
    private String ruc;

    @Column(columnDefinition = "VARCHAR(12)")
    private String phone;

    private boolean status = true;

    public Provider(){

    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getRuc(){
        return this.ruc;
    }

    public void setRuc(String ruc){
        this.ruc = ruc;
    }

    public String getPhone(){
        return this.phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
