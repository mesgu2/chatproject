package user;

import org.junit.Test;

import static org.junit.Assert.*;

public class userControllerTest {

    @Test
    public void authenticate() {
        userDb list = new userDb();
        userController test = new userController();

        list.insert("user1", "password1");
        list.insert("user2", "password2");
        list.insert("user3", "password3");

        list.display();

        if(test.authenticate(list, "user1", "password1") == true)
        {
            System.out.println("Logged In");
        }

        else
        {
            System.out.println("Access Denied");
        }

        if(test.authenticate(list, "user4", "password4") == true)
        {
            System.out.println("Logged In");
        }

        else
        {
            System.out.println("Access Denied");
        }

        if(test.authenticate(list, "user1", "password2") == true)
        {
            System.out.println("Logged In");
        }

        else
        {
            System.out.println("Access Denied");
        }
    }

}