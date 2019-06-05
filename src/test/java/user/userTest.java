package user;

import org.junit.Before;
import org.junit.Test;
import user.user;

import static org.junit.Assert.*;

public class userTest {

    @Test
    public void getUsername() {
        user test = new user("user1", "password");

        assertSame("user1", test.getUsername());
    }

    @Test
    public void display() {
        user test = new user("user0", "password");
        user test1 = new user("user1", "password!");

        test.display();
        test1.display();
    }
}