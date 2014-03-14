

package com.springapp.mvc;

public class Login {

    String login;
    String password;

    public Login() {

    }

    public Login(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

}
