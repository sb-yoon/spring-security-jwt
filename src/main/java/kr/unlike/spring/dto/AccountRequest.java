package kr.unlike.spring.dto;

import lombok.Data;

@Data
public class AccountRequest {
    private String email;
    private String password;
    private String name;
    private String gender;
}
