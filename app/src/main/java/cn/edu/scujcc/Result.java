package cn.edu.scujcc;

/**
 * 服务器返回的消息。
 */
public class Result<T> {
    public final static int OK = 1;
    public final static int ERROR = 0;
    private int status;
    private String message;
    private T data;
//你不那么你就吼吼吼吼
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}