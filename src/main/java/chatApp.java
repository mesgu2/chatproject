import io.javalin.Javalin;
import io.javalin.websocket.WsSession;
import java.text.SimpleDateFormat;
import java.util.Date;

//may not be needed after I set up my database
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.jetty.websocket.api.Session;
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
                b(sender + " says"),
                span(attrs(".timstamp"), new SimpleDateFormat("HH:mm:ss").format(new Date())),
                p(message)
        ).render();
    }

}
