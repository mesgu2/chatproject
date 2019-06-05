/*
Author: Montana Esguerra
Class: CS300
Filename: userNode.java
Description: user objects with an added next pointer
 */
package user;

public class userNode {

    //Made public for prototyping
    public user data;
    public userNode next;

    //default constructor
    public userNode() {
        this.data = new user();
        next = null;
    }

    //parameterized constructor
    public userNode(String username, String password) {
        this.data = new user(username, password);
        next = null;
    }

    //Returns the next pointer
    public userNode getNext() {
        return next;
    }

    //Sets the next pointer
    public void setNext(userNode next) {
        this.next = next;
    }

    //Displays the contents of the userNode
    public void display() {
        data.display();
    }
}
