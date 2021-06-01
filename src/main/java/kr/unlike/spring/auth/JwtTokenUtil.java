package kr.unlike.spring.auth;

import kr.unlike.spring.domain.Account;
import kr.unlike.spring.exception.InvalidTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class JwtTokenUtil {

    public static Account getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new InvalidTokenException("유효하지 않는 토큰입니다.", 401);
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getAccount();
    }
}
