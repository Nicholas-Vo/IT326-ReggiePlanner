package edu.illinoisstate.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EmailAuthentication {
    private final String USERNAME = "ReggiePlanner@gmail.com";

    public InternetAddress getProgramEmail() throws AddressException {
        return new InternetAddress(USERNAME);
    }

    /**
    Obtain password for the email from resources/emailpassword file to avoid
     pushing the password in plain text to GitHub
     */
    public String getPasswordFromFile() {
        BufferedReader lineReader;
        String password = null;
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            var myVar = classloader.getResource("emailpassword" );
            lineReader = new BufferedReader(new FileReader(myVar.getFile()));
            password = lineReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return password;
    }

    public Session getAuthenticator() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", true); //authentication
        properties.put("mail.smtp.starttls.enable", true); //encryption
        properties.put("mail.smtp.password", getPasswordFromFile()); //or port 587

        return Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, getPasswordFromFile());
            }
        });
    }
}
