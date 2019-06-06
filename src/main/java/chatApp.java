import io.javalin.Context;
import io.javalin.Javalin;
import io.javalin.websocket.WsSession;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

//may not be needed after I set up my database
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.jetty.server.session.SessionData;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.websocket.api.*;
import org.json.JSONObject;
import static j2html.TagCreator.article;
import static j2html.TagCreator.attrs;
import static j2html.TagCreator.b;
import static j2html.TagCreator.p;
import static j2html.TagCreator.span;

//packages I have created
import login.*;
import user.*;


public class chatApp {

    private static Map<WsSession, user> userList = new ConcurrentHashMap<>();
    private static Map<WsSession, String> userNameList = new ConcurrentHashMap<>();

    public static void main(String[] args) {


        userDb users = new userDb();

        user user1 = new user("mario", "mario");
        user user2 = new user("peach", "princess");
        user user3 = new user("luigi", "they$e3MeR0l1inG");

        /*
        SessionHandler sh1 = new SessionHandler();
        SessionData sd1 = new SessionData();
        WsSession test1 = new WsSession("id", defaultSession, sh1, sd1,"example");
        */

        //Sample users used for testing
        users.insert("mario", "mario");
        users.insert("peach", "princess");
        users.insert("luigi", "theyseemerollin");

        Javalin app = Javalin.create()
                .port(7000)
                .enableStaticFiles("/public")
                .ws("/chat", wsHandler -> {
                    wsHandler.onConnect(session -> {
                        user newUser = new user("mario", "mario");
                        session.send("session: " + session);
                        userList.put(session, newUser);
                        userNameList.put(session, newUser.username);
                        broadcastMessage("Server", (newUser.username + " joined the chat"));
                    });
                    wsHandler.onClose(((wsSession, status, message) -> {
                        user newUser = new user(userList.get(wsSession).username, userList.get(wsSession).password);
                        userList.remove(wsSession);
                        userNameList.remove(wsSession);
                        broadcastMessage("Server", (newUser.username + " left the chat"));
                    }));
                    wsHandler.onMessage((session, message) -> {
                        broadcastMessage(userNameList.get(session), message);
                    });

                })
                .start();

/*
        Javalin app= Javalin.create()
                .start(7000)
                .get("/", context -> context.result(users.display()));
*/
    }

    /*
    code created by tipsy and taken from javalin tutorial
     */
    //sends a message from one user to all users
    private static void broadcastMessage(String sender, String message) {
        userNameList.keySet().stream().filter(Session::isOpen).forEach(wsSession -> {
            wsSession.send(
                    new JSONObject()
                            .put("userMessage", createHtmlMessageFromSender(sender, message))
                            .put("userlist", userNameList.values()).toString()

            );
        });
    }


    /*
    code created by tipsy and taken from javalin tutorial
     */
    //Builds an html element with a sender name, a message, and a timestamp
    private static String createHtmlMessageFromSender(String sender, String message) {
        return article(
                b(sender + " at "),
                span(attrs(".timstamp"), new SimpleDateFormat("HH:mm:ss").format(new Date())),
                p(message)
        ).render();
    }

}
