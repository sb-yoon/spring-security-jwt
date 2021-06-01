package kr.unlike.spring.auth;

import kr.unlike.spring.domain.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

public class UserDetails extends User {

    private final Account account;

    public UserDetails(Account account) {
        super(account.getEmail(), account.getPwd(), authorities(account.getRole()));
        this.account = account;
    }

    private static Collection<? extends GrantedAuthority> authorities(String roles) {
        return Collections.singleton(new SimpleGrantedAuthority(roles));
    }

    public Account getAccount() {
        return this.account;
    }
}
