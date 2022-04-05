package edu.illinoisstate.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class EmailAuthentication {
    private final String USERNAME = "ReggiePlanner@gmail.com";

    public InternetAddress getProgramEmail() throws AddressException {
        return new InternetAddress(USERNAME);
    }

    /**
     * Obtain password for the email from resources/emailpassword file to avoid
     * pushing the password in plain text to GitHub
     */
    public String getPasswordFromFile() {
        String password = null;

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL theResource = classloader.getResource("emailpassword"); // obtain URL

            BufferedReader lineReader = new BufferedReader(new FileReader(theResource.getFile())); // create reader
            password = lineReader.readLine(); // read the password
        } catch (IOException e) {
            e.printStackTrace();
        }
        return password;
    }

    /**
     * Set up email session
     * @return Created Session object
     */
    public Session getSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", true); // authentication
        properties.put("mail.smtp.starttls.enable", true); // encryption
        properties.put("mail.smtp.password", getPasswordFromFile());

        return Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, getPasswordFromFile());
            }
        });
    }
}
