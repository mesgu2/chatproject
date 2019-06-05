
/*
Author: Montana Esguerra
Class: CS 300
Filename: user.java
 */

package user;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class user {
    public String username;
    public String password;

    //default constructor
    public user() {
        this.username = "guest";
        this.password = getMd5("password");
    }

    //parameterized constructor
    public user(String username, String password) {
        this.username = username;
        this.password = getMd5(password);
    }

    //Returns the username of the user class
    public String getUsername() {
        return this.username;
    }

    //Returns the hashed password of the user class
    public String getPassword() {
        return this.password;
    }

   //Creates an md5 hash of a given String
    public static String getMd5(String input)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32)
            {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    //Displays the user information
    public void display() {
        System.out.println("Username: "  + this.username);
        System.out.println("Hashed Password: " + this.password);
        System.out.println("=======================");
    }

}
