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


public class chatApp {

    public static void main(String[] args) {

        Javalin app= Javalin.create()
                .start(7000)
                .get("/", context -> context.result("Hello, World"));

    }
}
