# chatproject
## (chat app 1.1)

### NOTICE: This project leans heavily on tutorials provided by David Ase (tipsy) Checkout his project at https://github.com/tipsy/javalin-websocket-example

![ChatAppDemo](/chatAppDemo.png)

# Summary

This project is a chat application that consists of a server and allows for an indefinite number of users to login and begin chatting with other users. This application allows users to register accounts and login.
Once logged in, users can broadcast messages to all online users or direct message users from a list of users.
The chat supports time stamps, user login notification, and the ability to grab a copy of their chat history.

This project was written in Java and built on top of the Javalin framework. Checkout their site for tutorials on how to 
get started with [Javalin](https://javalin.io/tutorials)

# Features
* Register account
* Login
* Broadcast Message
* Direct Message
* Timestamped messages
* Login Notifications
* Chat History

# Login Page
![loginPage](/chatAppLogin.png)

## Popup Login that links to a Register Page
![loginPopUp](/loginPopUp.png)

# Known Faults

* Login currently provides no form of authentication
* Every user that connects will be named mario
* User List does not scale properly for all screen types

# TO DO
* Create a register page
* Implement get chat history feature
* Implement register feature
* Implement direct messaging
* Implement logout button

# Demo
Want to try out my current build? Please feel free to clone my repository. I've also included a jar file which is located in /out.
Run the jar and go to http://localhost:7000
## Steps
* Run chatproject.jar
* Open a browser and go to http://localhost:7000
* Enjoy trying it out!
