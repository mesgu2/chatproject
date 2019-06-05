package user;

import org.junit.Test;

import static org.junit.Assert.*;

public class userDbTest {

    @Test
    public void insert() {
    }

    @Test
    public void display() {
        userDb testDb = new userDb();
        testDb.insert("user1", "password");
        testDb.insert("user2", "password2");
        testDb.insert("user3", "password3");

        testDb.display();
    }
}