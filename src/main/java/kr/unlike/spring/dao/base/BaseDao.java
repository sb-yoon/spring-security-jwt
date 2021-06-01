package kr.unlike.spring.dao.base;

import java.util.List;

public interface BaseDao<T> {
    List<T> selectList();
    T selectOne(Integer id);
    T selectOne(Long id);
    int insert(T t);
    int update(T t);
    int delete(Integer id);
    int delete(Long id);
}
