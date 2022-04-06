package edu.illinoisstate.email;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.utils.Security;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public class EmailHandler {

    /**
     * Send a password reset email
     *
     * @param user: The email recipient
     */
    public void sendPasswordReset(UserAccount user) {
        String body = """
                Dear ReggiePlanner user,
                                
                We've received a request to reset your ReggiePlanner account.

                Here's a temporary password we've generated for you:
                                
                """

                + Security.generateRandomPassword(user)

                + "\n\nIf you didn't request this, you can safely ignore this email.";

        EmailSender.send(user.email(), "Password reset link from ReggiePlanner", body);
        System.out.println("Sent password reset email to " + user.getUsername() + " .");
    }

    /**
     * Send an account confirmation email
     *
     * @param username:  The created username
     * @param recipient: The email recipient
     */
    public void sendAccountConfirmation(String username, String recipient) {
        String body = "Dear " + username + ", " +
                """                          
                        Welcome to ReggiePlanner!

                        Please click on the link below to verify your email with us.
                                        
                        """

                + "\n\nIf you didn't request this, you can safely ignore this email.";

        EmailSender.send(recipient, "Verify your email with ReggiePlanner", body);
    }

    /**
     * Subclass to handle sending of emails
     */
    private static class EmailSender {

        public static void send(String recipient, String subject, String body) {
            /*
            runnable used to avoid hanging entire program when sending email
             */
            new Thread(() -> {
                EmailAuthentication authentication = new EmailAuthentication();
                Session session = authentication.getSession(); // new session for authentication
                Message theEmail = new MimeMessage(session); // creates a new message template to send email to user

                try {
                    theEmail.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                    theEmail.setFrom(authentication.getProgramEmail());
                    theEmail.setSubject(subject);
                    theEmail.setText(body);
                    theEmail.setSentDate(new Date());

                    // add text to email
                    session.getTransport("smtp");
                    Transport.send(theEmail);
                    // todo log this action
                } catch (Exception e) {
                    e.printStackTrace();
                    // todo log failure
                }
            }).start();
        }

    }

}