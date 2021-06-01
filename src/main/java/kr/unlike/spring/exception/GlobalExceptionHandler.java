package kr.unlike.spring.exception;
import com.fasterxml.jackson.core.JsonParseException;
import kr.unlike.spring.dto.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> handler(MultipartException e, HttpServletRequest request) {
        log.error(e.getMessage());
        String message = "파일은 1MB 이하로 업로드 가능합니다.";
        String contextPath = request.getContextPath();
        log.error("contextPath: {}", contextPath);
        return ApiResponse.error(message);
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handler(TypeMismatchException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return ApiResponse.error(e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handler(MissingServletRequestParameterException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return ApiResponse.error(e.getMessage());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handler(MaxUploadSizeExceededException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return ApiResponse.error(e.getMessage());
    }

    @ExceptionHandler(ValidateException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> handler(ValidateException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return ApiResponse.error(e.getMessage(), e.getCode());
    }

    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> handler(BizException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return ApiResponse.error(e.getMessage(), e.getCode());
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> handler(InvalidTokenException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return ApiResponse.error(e.getMessage(), e.getCode());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ApiResponse<?> handler(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return ApiResponse.error(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handler(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return ApiResponse.error(e.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ApiResponse<?> handler(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return ApiResponse.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<?> handler(Exception e, HttpServletRequest request) {
        log.error(e.getMessage());
        return ApiResponse.error(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handler(NotFoundException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return ApiResponse.error(e.getMessage());
    }
}
