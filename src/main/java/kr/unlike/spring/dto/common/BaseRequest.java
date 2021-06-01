package kr.unlike.spring.dto.common;

public interface BaseRequest<T> {
    T toEntity();
}
