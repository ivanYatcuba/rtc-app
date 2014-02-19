package com.springapp.mvc;

public class User {
    private String fio;
    private String phone;
    private String email;
    private int birthYear;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }



    public User(){

    }

    public User(String fio, String phone, String email, int birthYear){
        this.fio = fio;
        this.phone = phone;
        this.email = email;
        this.birthYear = birthYear;

    }

}
