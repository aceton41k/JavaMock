package saushkin.javamock;


import org.json.JSONException;
import org.json.JSONObject;
import saushkin.javamock.model.User;
import saushkin.javamock.service.UserService;

import java.sql.SQLException;
import java.util.List;

import static spark.Spark.*;

public class Api {


    public static void start() throws SQLException {
        UserService us = new UserService();
        port(8080);
        get("/getUsers", (req, res) -> {
            res.type(ContentType.JSON);
            List users = us.findAllUsers();
            return new JSONResponse("success", "ok", new JSONObject().put("total", users.size()).put("users", users));
        });

        get("/getUser/:id", (req, res) -> {
            res.type(ContentType.JSON);
            int id = Integer.parseInt(req.params(":id"));
            return new JSONResponse("success", "ok", new JSONObject().put("user", new JSONObject(us.getUser(id))));
        });

        post("/addUser", (req, res) -> {
            res.type(ContentType.JSON);
            JSONObject jso;
            try {
                jso = new JSONObject(req.body());
            } catch (JSONException e) {
                return new JSONResponse("error", e.getMessage(), new JSONObject().put("Stacktrace", e.getStackTrace()));
            }
            String firstName = jso.getString("firstName");
            String lastName = jso.getString("lastName");
            String middleName = jso.getString("middleName");
            int role_id = jso.getInt("role_id");
            int id = us.addUser(new User(firstName, lastName, middleName, role_id));
            return new JSONResponse("success", "Successfully added", new JSONObject().put("added_id", id));
            //return new JSONObject(jsr);
        });

        delete("/delUser", (req, res) -> {
            res.type(ContentType.JSON);
            JSONObject jso;
            try {
                jso = new JSONObject(req.body());
            } catch (JSONException e) {
                return new JSONResponse("error", e.getMessage(), new JSONObject().put("Stacktrace", e.getStackTrace()));
            }
            int id = jso.getInt("id");
            if (us.deleteUser(id)) {
                return new JSONResponse("success", "User id=" + id + " deleted");
            } else return new JSONResponse("fail", "User id=" + id + " not found");
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
