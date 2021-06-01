package kr.unlike.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.unlike.spring.domain.Board;
import kr.unlike.spring.dto.common.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse implements BaseResponse<BoardResponse, Board> {

    private long id;

    private String category;

    private String title;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regDate;

    private AccountInfo account;

    @Override
    public BoardResponse of(Board board) {
        return builder()
                .id(board.getId())
                .category(board.getCategory())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .account(board.getAccount())
                .build();
    }

    @Override
    public List<BoardResponse> of(List<Board> list) {
        return list.stream()
                .map(this::of)
                .collect(Collectors.toList());
    }
}
