package kr.unlike.spring.dao;

import kr.unlike.spring.dao.base.BaseDao;
import kr.unlike.spring.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardDao extends BaseDao<Board> {
    List<Board> selectByAccountId(Long accountId);
}
