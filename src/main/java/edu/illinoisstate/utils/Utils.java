package edu.illinoisstate.utils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Utils {

    /**
     * Hash a given string
     *
     * @param theString: The string to hash
     * @return a hashed String
     */
    public static String hash(String theString) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Charset charSet = StandardCharsets.UTF_8;
        return new String(digest.digest(theString.getBytes(charSet)), charSet);
    }

    /**
     * add white space to GUI
     * @param panel the panel
     * @param size amount of space to add
     */
    public static void addWhiteSpace(JPanel panel, int size) {
        panel.add(new JToolBar.Separator(new Dimension(20, size)));
    }

    /**
     * Get an image from file
     * @param name name of the image
     * @return the Image object
     */
    public static Image getImage(String name) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource("images/" + name);
        return new ImageIcon(Objects.requireNonNull(url)).getImage();
    }

}
