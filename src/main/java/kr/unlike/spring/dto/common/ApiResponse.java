package kr.unlike.spring.dto.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse<T> {

    // api 성공 여부
    private boolean success;

    // api 응답 메세지
    private String msg;

    // api 응답 코드
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int code;

    // api 통신시간
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transactionTime;

    // DATA
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static ApiResponse<?> ok() {
        ApiResponse<?> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMsg("OK");
        response.setTransactionTime(new Date());
        response.setCode(200);
        return response;
    }

    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMsg("OK");
        response.setTransactionTime(new Date());
        response.setCode(200);
        response.setData(data);
        return response;
    }

    public static ApiResponse<?> error(String message) {
        ApiResponse<?> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMsg(message);
        response.setTransactionTime(new Date());
        return response;
    }

    public static ApiResponse<?> error(String message, int code) {
        ApiResponse<?> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMsg(message);
        response.setCode(code);
        response.setTransactionTime(new Date());
        return response;
    }
}
