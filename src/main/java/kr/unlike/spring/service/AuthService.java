package kr.unlike.spring.service;

import kr.unlike.spring.auth.JwtTokenProvider;
import kr.unlike.spring.dao.AccountDao;
import kr.unlike.spring.domain.Account;
import kr.unlike.spring.dto.TokenResponse;
import kr.unlike.spring.dto.AccountRequest;
import kr.unlike.spring.exception.BizException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountDao accountDao;

    @Transactional(readOnly = true)
    public Account findOneByEmail(String email) {
        return accountDao.selectOneByEmail(email);
    }

    @Transactional
    public void signup(Account account) {
        accountDao.register(account);
    }

    @Transactional
    public TokenResponse login(AccountRequest accountRequest) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(accountRequest.getEmail(), accountRequest.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            return TokenResponse.builder().token(jwtTokenProvider.generateToken(authentication.getName(), "USER")).build();
        } catch (AuthenticationException e) {
            throw new BizException("아이디 또는 비밀번호가 일치하지 않습니다", 993);
        }

    }
}
