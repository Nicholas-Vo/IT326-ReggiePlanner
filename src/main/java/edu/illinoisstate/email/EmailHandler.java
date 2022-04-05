package edu.illinoisstate.email;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.utils.Utils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public class EmailHandler {

    /**
     * Send a password reset email
     * @param recipient: The email recipient
     * @return false if the email fails to send
     */
    public boolean sendPasswordReset(String recipient) {
        String body = """
                Dear ReggiePlanner user,
                                
                We've received a request to reset your ReggiePlanner account.

                Here's a temporary password we've generated for you:
                                
                \s"""

                + Utils.generateRandomPassword()

                + "\n\nIf you didn't request this, you can safely ignore this email.";

        return EmailSender.send(recipient, "Password reset link from ReggiePlanner", body);
    }

    /**
     * Send an account confirmation email
     * @param username: The created username
     * @param recipient: The email recipient
     * @return false if the email fails to send
     */
    public boolean sendAccountConfirmation(String username, String recipient) {
        String body = "Dear " + username + ", " +
                """                          
                        Welcome to ReggiePlanner!

                        Please click on the link below to verify your email with us.
                                        
                        \s"""

                + "\n\nIf you didn't request this, you can safely ignore this email.";

        return EmailSender.send(recipient, "Verify your email with ReggiePlanner", body);
    }

    /**
     * Subclass to handle sending of emails
     */
    private static class EmailSender {

        public static boolean send(String recipient, String subject, String body) {
            EmailAuthentication authentication = new EmailAuthentication();
            Session session = authentication.getAuthenticator(); // new session for authentication
            Message theEmail = new MimeMessage(session); // creates a new message template to send email to user

            try {
                InternetAddress programEmail = new InternetAddress(authentication.getProgramEmail());
                theEmail.setFrom(programEmail);
                theEmail.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                theEmail.setSubject(subject);
                theEmail.setText(body);
                theEmail.setSentDate(new Date());

                // add text to email
                session.getTransport("smtp");
                Transport.send(theEmail);
                // todo log this action
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                // todo log failure
            }

            return false;
        }

    }

}