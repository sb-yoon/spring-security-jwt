package kr.unlike.spring.dto;

import kr.unlike.spring.domain.Board;
import kr.unlike.spring.dto.common.BaseRequest;
import lombok.Getter;

@Getter
public class BoardRequest implements BaseRequest<Board> {

    private String category;
    private String title;
    private String content;

    @Override
    public Board toEntity() {
        return Board.builder()
                .category(this.category)
                .title(this.title)
                .content(this.content)
                .build();
    }
}
