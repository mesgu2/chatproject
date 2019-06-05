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
    public int insert(userNode to_insert) {
        if(head == null) {
            head = new userNode(to_insert.data.username, to_insert.data.password);
            return 0;
        }

        userNode newNode = new userNode(to_insert.data.username, to_insert.data.password);
        newNode.setNext(this.head);
        this.head = newNode;

        return 1;
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
