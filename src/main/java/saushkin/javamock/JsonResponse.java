package saushkin.javamock;

import java.util.List;

public class JsonResponse {
    String status;
    String message;
    public List<?> data;

    public JsonResponse(String status, String message, List<?> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public JsonResponse(String status, String message) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
