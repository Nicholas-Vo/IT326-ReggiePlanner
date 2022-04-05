package edu.illinoisstate;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Properties;

//import edu.illinoisstate.database.DatabaseHandler;


public class Email {

    /*
    Todo: Make this actually send email + make logger class, log this action
     */

    private static final String USERNAME = "test@gmail.com";
    private static final String PASSWORD = "test";
    private static final String SUBJECT = "Reset Password link";
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "465";

    //String recipient;
    //String uuid;


    /*
    Servlet request for getting into HTTP Servers
     */
    public boolean sendEmail(String to, HttpServletRequest request) throws ServletException {


        /*
        DATABASE
        Need to add code for connection to User Database
        Need to add code to get specific user from Database

        1. Connect to Database
        2. Query to retrieve specific user information (email, UUID)
        3. Execute that query
        4. ...
         */

        /*SMTP (Simple Mail Transfer Protocol) server properties
        (need to find outlook smtp properties!!)
        creates instance of properties class
        specifies IP address for generic server

        Properties used to load streams of data
         */
        Properties properties = new Properties();

        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", PORT); //or port 587
        properties.put("mail.smtp.auth", "true"); //authentication
        properties.put("mail.smtp.starttls.enable", "true"); //encryption
        properties.put("mail.smtp.password", PASSWORD); //or port 587


        //new session for authentication
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                String username = USERNAME;
                String password = PASSWORD;
                return new PasswordAuthentication(username, password);
            }
        });


        //creates a new message template to send email to user
        Message message1 = new MimeMessage(session);
        try {
            message1.setFrom(new InternetAddress(USERNAME));
            InternetAddress toUserAddress = new InternetAddress(to);
            message1.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message1.setSubject(SUBJECT);
            message1.setSentDate(new Date());


            //add text to email


            session.getTransport("smtp");
            Transport.send(message1);

            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }


    }


    public static void sendForgotPassword(String recipient) {
        System.out.println("Email sent to" + recipient);
    }


}
