package kr.unlike.spring.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {

    private static final long serialVersionUID = 4656084497370658912L;

    private final Integer code;

    public InvalidTokenException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
