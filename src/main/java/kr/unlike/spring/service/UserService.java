package kr.unlike.spring.service;

import kr.unlike.spring.auth.UserDetails;
import kr.unlike.spring.dao.AccountDao;
import kr.unlike.spring.domain.Account;
import kr.unlike.spring.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final AccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String id) {
        Account account = accountDao.selectOneByEmail(id);
        if (account == null) {
            throw new NotFoundException("존재하지 않는 회원입니다.", 990);
        }

        return new UserDetails(account);
    }
}
