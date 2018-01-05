package ruolan.com.friendcircledemo.model;

/**
 * Created by wuyinlei on 2018/1/5.
 *
 * @function
 */

public class HttpResult<T> extends BaseModel{

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
}
