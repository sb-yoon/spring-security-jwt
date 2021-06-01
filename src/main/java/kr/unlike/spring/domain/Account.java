package kr.unlike.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id; // 회원 식별자
    private String email; // 이메일
    private String pwd; // 비밀번호
    private String nickname; // 닉네임
    private String name; // 이름
    private String gender; // 성별
    private String role; // 회원 구분
    private Date regDate; // 등록일시
    private Date modDate; // 수정일시
}
