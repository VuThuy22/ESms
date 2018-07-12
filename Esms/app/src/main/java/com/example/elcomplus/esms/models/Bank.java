package com.example.elcomplus.esms.models;

public class Bank {
    private int id;
    private String phone;

    public Bank(int id, String phone) {

        this.id = id;
        this.phone = phone;
    }
    public Bank() {

    }

    public Bank(int id) {
        this.id = id;
    }

    public Bank(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                '}';
    }
}
