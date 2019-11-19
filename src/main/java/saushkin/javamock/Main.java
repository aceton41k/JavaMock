package saushkin.javamock;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
//        Api.start();
        DB db = DB.getInstance();
        JsonResponse jsr;
        try {
            jsr = new JsonResponse("success", "ok", db.getUsers());
        } catch (SQLException e) {

            List<StackTraceElement> stack = Arrays.asList(e.getStackTrace());
            //stack.add(e.getStackTrace());
            jsr = new JsonResponse("error", e.getMessage(), stack);

        }

        JSONObject jso = new JSONObject(jsr);

//        JSONArray jsa = new JSONArray(jso);
//        jsa.put(0, jso);

        System.out.println(jso);
//        System.out.println(jsa.toString());



//        User user = new User();
//        user.setFirstName("Александр");
//        user.setLastName("Пушкин");
//        user.setMiddleName("Сергеевич");
//        user.setRole("admin");
//        DB db = DB.getInstance();
//        db.addUser(user);

    }
}
