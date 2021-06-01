package kr.unlike.spring.controller;

import kr.unlike.spring.dto.BoardRequest;
import kr.unlike.spring.dto.BoardResponse;
import kr.unlike.spring.dto.common.ApiResponse;
import kr.unlike.spring.service.BoardService;
import kr.unlike.spring.util.ValidateUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/board")
@AllArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public ApiResponse<List<BoardResponse>> getBoardList() {
        return ApiResponse.ok(boardService.findAll());
    }

    @GetMapping("/me")
    public ApiResponse<List<BoardResponse>> getMyBoardList() {
        return ApiResponse.ok(boardService.findAllByUser());
    }

    @GetMapping("/{id}")
    public ApiResponse<BoardResponse> getBoard(@PathVariable Long id) {
        return ApiResponse.ok(boardService.findOne(id));
    }

    @PostMapping
    public ApiResponse<?> saveBoard(@RequestBody BoardRequest boardRequest) {
        ValidateUtil.notEmpty(boardRequest.getCategory(), "카테고리를 확인해주세요.");
        ValidateUtil.notEmpty(boardRequest.getTitle(), "제목을 확인해주세요.");
        ValidateUtil.notEmpty(boardRequest.getContent(), "내용을 확인해주세요.");

        boardService.saveBoard(boardRequest);
        return ApiResponse.ok();
    }

    @PatchMapping("/{id}")
    public ApiResponse<?> updateBoard(@PathVariable Long id, @RequestBody BoardRequest boardRequest) {
        boardService.updateBoard(id, boardRequest);
        return ApiResponse.ok();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ApiResponse.ok();
    }
}
