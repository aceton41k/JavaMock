package saushkin.javamock;

import org.json.JSONObject;

import java.util.List;

/**
 * Класс для возврата ответа в виде Json со стандартными полями
 */

public class JSONResponse extends JSONObject {
    String status;
    String message;
    JSONObject data;

    public JSONResponse(String status, String message, List<?> data) {
        this.put("status", status);
        this.put("message", message);
        this.put("data", data);
    }

    public JSONResponse(String status, String message, JSONObject data) {
        this.put("status", status);
        this.put("message", message);
        this.put("data", data);
//        this.status = status;
//        this.message = message;
//        this.data = data;
//        this.aa = "aa";
    }

    public JSONResponse(String status, String message) {
        this.put("status", status);
        this.put("message", message);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
