package kr.unlike.spring.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7813232951033246058L;

    private Integer code;

    public NotFoundException(String msg, Exception e) {
        super(msg, e);
    }

    public NotFoundException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
