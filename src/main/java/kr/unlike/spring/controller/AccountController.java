package kr.unlike.spring.controller;

import kr.unlike.spring.auth.JwtTokenUtil;
import kr.unlike.spring.domain.Account;
import kr.unlike.spring.dto.AccountResponse;
import kr.unlike.spring.dto.common.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class AccountController {

    @GetMapping("/me")
    public ApiResponse<AccountResponse> getMe() {
        Account account = JwtTokenUtil.getCurrentUser();
        return ApiResponse.ok(new AccountResponse().of(account));
    }
}
