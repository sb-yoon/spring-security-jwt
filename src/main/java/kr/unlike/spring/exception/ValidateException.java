package kr.unlike.spring.exception;

public class ValidateException extends RuntimeException {

    private Integer code;

    private static final long serialVersionUID = -9218894624533726526L;

    public ValidateException(String msg, Exception e) {
        super(msg, e);
    }

    public ValidateException(String msg) {
        super(msg);
        this.code = 400;
    }

    public ValidateException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
