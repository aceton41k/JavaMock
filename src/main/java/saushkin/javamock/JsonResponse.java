package saushkin.javamock;

import org.json.JSONObject;

/**
 * Класс для возврата ответа в виде Json со стандартными полями
 */

public class JsonResponse extends JSONObject {
    String status;
    String message;
    JSONObject data;

    public JsonResponse(String status, String message, JSONObject data) {
        this.put("status", status);
        this.put("message", message);
        this.put("data", data);
//        this.status = status;
//        this.message = message;
//        this.data = data;
//        this.aa = "aa";
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
