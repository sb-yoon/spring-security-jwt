package kr.unlike.spring.domain;

import kr.unlike.spring.dto.AccountInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private Long id;
    private String category;
    private String title;
    private String content;
    private Date regDate;
    private Date modDate;
    private Long accountId;

    private AccountInfo account;
}