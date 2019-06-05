/*
Author: Montana Esguerra
Class: CS 300
Filename: userDb.java
Description: a database of users. This database is a linearly linked list
 */
package user;

public class userDb {
    //made public prototyping
    public userNode head;

    //default constructor
    public userDb() {
        head = null;
    }

    //inserts userNodes at the head of the list
    //Essentially a stack's push implementation
    //Will not insert a new user if that username is already being used.
    //Returns 1 on failure
    public int insert(userNode to_insert) {
        if(head == null) {
            head = new userNode(to_insert.data.username, to_insert.data.password);
            return 0;
        }

        //Checks first if the username is already in the database
        if(findUser(to_insert.data.username) == null) {
            return 1;
        }

        userNode newNode = new userNode(to_insert.data.username, to_insert.data.password);
        newNode.setNext(this.head);
        this.head = newNode;

        return 0;
    }

    //Insert method that takes Strings as parameters
    public int insert(String username, String password) {
        if(head == null) {
            head = new userNode(username, password);
            return 0;
        }

        //Checks first if the username is already in the database
        if(findUser(username) == null) {
            return 1;
        }

        userNode newNode = new userNode(username, password);
        newNode.setNext(this.head);
        this.head = newNode;

        return 0;
    }

    //Searches the database by username. If the user is found, returns user.
    //If not, returns null. If null is returned, then the user was not found in the database
    public user findUser(String username) {
        userNode current = head;

        while(current != null) {
            if(username.compareTo(current.data.username) == 0) {
                return current.data;
            }

            current = current.getNext();
        }

        return null;
    }

    //Displays the users of database
    public void display() {
        userNode current = head;

        while(current != null) {
            current.display();
            current = current.getNext();
        }
    }


}
