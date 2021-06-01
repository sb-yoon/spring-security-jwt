package kr.unlike.spring.controller;

import kr.unlike.spring.dto.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public ApiResponse<String> welcome() {
        return ApiResponse.ok("Welcome Spring Security Jwt");
    }
}