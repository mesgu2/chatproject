/*
Author: Montana Esguerra
Class: CS 300
Filename: userController.java
 */
package user;

import static user.user.getMd5;

public class userController {

    //Authenticates the user. If the username is not in the database, return false.
    //If the username is in the database, the passed in password is hashed and the
    //hash is compared to the hashed password. If these do not match, return false.
    //If the hashed password and the username matches, return true.
    public static boolean authenticate(userDb database, String username, String password) {
        if(username == null || password == null)
            return false; //If both username and password are null, do not authenticate

        boolean result = false;

        user test = database.findUser(username);

        //match not made FIXME There is a clear problem with this design but for now I'm using it
        if(test.username.compareTo("dont'PickThis") == 0)
            return false;

        String testPassword = getMd5(password);

        if(testPassword.compareTo(test.password) == 0)
            result = true;


        return result;
    }

}
