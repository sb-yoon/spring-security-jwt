package kr.unlike.spring.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface BaseResponse<E, T> {
    E of(T t);
    List<E> of(List<T> list);
}
