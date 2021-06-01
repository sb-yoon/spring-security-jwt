package kr.unlike.spring.dao;

import kr.unlike.spring.dao.base.BaseDao;
import kr.unlike.spring.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountDao extends BaseDao<Account> {
    void register(Account Account);
    Account selectOneByEmail(String email);
}
