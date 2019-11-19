package saushkin.javamock;


import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;

import static spark.Spark.*;

public class Api {
    public static void start() throws SQLException {

        port(8080);
        get("/getUsers", (req, res) -> {
            res.type("application/json");
            DB db = DB.getInstance();

            JSONArray jsa = new JSONArray(db.getUsers());
            jsa.put("success");
            return jsa.toString();

        });

//        get("/hello", (req, res) -> {
//            //res.body("\"success\": true");
//            //res.status(403);
//            res.type("application/json");
//            return "{\"success\": true}";
//
//        });



//        post("/addUser", (req, res) -> );
    }
}
