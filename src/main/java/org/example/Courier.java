package org.example;

public class Courier {
    private String login;
    private String password;
    private String firstName;

    public Courier(String login, String password, String firstName) {
        this.password = password;
        this.login = login;
        this.firstName = firstName;
    }
}
