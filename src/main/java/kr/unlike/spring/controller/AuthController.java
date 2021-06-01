package kr.unlike.spring.controller;

import kr.unlike.spring.domain.Account;
import kr.unlike.spring.dto.TokenResponse;
import kr.unlike.spring.dto.AccountRequest;
import kr.unlike.spring.dto.common.ApiResponse;
import kr.unlike.spring.exception.BizException;
import kr.unlike.spring.service.AuthService;
import kr.unlike.spring.util.ValidateUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<?> signup(@RequestBody AccountRequest accountRequest) {
        ValidateUtil.email(accountRequest.getEmail(), "이메일을 확인해주세요.");
        ValidateUtil.notEmpty(accountRequest.getPassword(), "비밀번호를 확인해주세요.");

        Account account = authService.findOneByEmail(accountRequest.getEmail());
        if (account != null) {
            throw new BizException("이미 가입되어 있는 회원입니다.", 991);
        }

        Account newAccount = Account.builder()
                .email(accountRequest.getEmail())
                .pwd(passwordEncoder.encode(accountRequest.getPassword()))
                .name(accountRequest.getName())
                .gender(accountRequest.getGender())
                .build();

        authService.signup(newAccount);
        return ApiResponse.ok();
    }

    @PostMapping("/login")
    public ApiResponse<TokenResponse> login(@RequestBody AccountRequest accountRequest) {
        ValidateUtil.email(accountRequest.getEmail(), "이메일을 확인해주세요.");
        ValidateUtil.notEmpty(accountRequest.getPassword(), "비밀번호를 확인해주세요.");

        TokenResponse tokenResponse = authService.login(accountRequest);
        return ApiResponse.ok(tokenResponse);
    }
}
