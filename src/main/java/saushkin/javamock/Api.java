package saushkin.javamock;


import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.Arrays;

import static spark.Spark.*;

public class Api {

    public static void start() throws SQLException {

        port(8080);
        get("/getUsers", (req, res) -> {
            res.type("application/json");
            DB db = DB.getInstance();
            JsonResponse jsr;
            try {
                jsr = new JsonResponse("success", "ok", db.getUsers());
            } catch (SQLException e) {
                jsr = new JsonResponse("error", e.getMessage(), Arrays.asList(e.getStackTrace()));
            }
            return new JSONObject(jsr);
        });

        post("/addUser", (req, res) -> {
            res.type("application/json");
            DB db = DB.getInstance();
            JSONObject jso;
            try {
                jso = new JSONObject(req.body());
            } catch (JSONException e) {
                return new JsonResponse("error", e.getMessage(), Arrays.asList(e.getStackTrace()));
            }
            String firstName = jso.getString("firstName");
            String lastName = jso.getString("lastName");
            String middleName = jso.getString("middleName");
            int role_id = jso.getInt("role_id");
            try {
                int id = db.addUser(firstName, lastName, middleName, role_id);
                return new JSONObject(new JsonResponse("success", "Successfully added", Arrays.asList(id)));
            } catch (SQLException e) {
                return new JSONObject(new JsonResponse("error", e.getMessage(), Arrays.asList(e.getStackTrace())));
            }
            //return new JSONObject(jsr);
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
