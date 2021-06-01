package kr.unlike.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.unlike.spring.domain.Account;
import kr.unlike.spring.dto.common.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse implements BaseResponse<AccountResponse, Account> {

    private long id;

    private String email;

    private String nickname;

    private String name;

    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regDate;

    @Override
    public AccountResponse of(Account account) {
        return builder()
                .id(account.getId())
                .email(account.getEmail())
                .nickname(account.getNickname())
                .name(account.getName())
                .gender(account.getGender())
                .regDate(account.getRegDate())
                .build();
    }

    @Override
    public List<AccountResponse> of(List<Account> list) {
        return null;
    }
}
