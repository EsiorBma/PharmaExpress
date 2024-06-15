package com.example.pharmaexpress;

import java.util.Date;

public class Utilisateur {

    String name, firtsname, email, password, location ;
    Date dateNaiss;

    public Utilisateur(String name, String firtsname, String email, String password, String location) {
        this.name = name;
        this.firtsname = firtsname;
        this.email = email;
        this.password = password;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirtsname() {
        return firtsname;
    }

    public void setFirtsname(String firtsname) {
        this.firtsname = firtsname;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "name='" + name + '\'' +
                ", firtsname='" + firtsname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
