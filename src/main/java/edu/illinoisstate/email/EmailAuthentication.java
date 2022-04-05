package edu.illinoisstate.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class EmailAuthentication {
    private final String USERNAME = "ReggiePlanner@gmail.com";
    private final String PASSWORD = "reggie500";

    public String getProgramEmail() {
        return USERNAME;
    }

    public Session getAuthenticator() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", true); //authentication
        properties.put("mail.smtp.starttls.enable", true); //encryption
        properties.put("mail.smtp.password", PASSWORD); //or port 587

        return Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
    }
}
