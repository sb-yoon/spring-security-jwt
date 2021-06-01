package kr.unlike.spring.exception;

public class BizException extends RuntimeException {

    private static final long serialVersionUID = 2447329833156858838L;

    private Integer code;

    public BizException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public BizException(String msg) {
        super(msg);
    }

    public Integer getCode() {
        return code;
    }
}
