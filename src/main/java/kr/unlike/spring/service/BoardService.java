package kr.unlike.spring.service;

import kr.unlike.spring.auth.JwtTokenUtil;
import kr.unlike.spring.dao.BoardDao;
import kr.unlike.spring.domain.Account;
import kr.unlike.spring.domain.Board;
import kr.unlike.spring.dto.BoardRequest;
import kr.unlike.spring.dto.BoardResponse;
import kr.unlike.spring.exception.BizException;
import kr.unlike.spring.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BoardService {

    private final BoardDao boardDao;

    @Transactional(readOnly = true)
    public List<BoardResponse> findAll() {
        return new BoardResponse().of(boardDao.selectList());
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> findAllByUser() {
        Account account = JwtTokenUtil.getCurrentUser();
        return new BoardResponse().of(boardDao.selectByAccountId(account.getId()));
    }

    @Transactional(readOnly = true)
    public BoardResponse findOne(Long id) {
        return new BoardResponse().of(boardDao.selectOne(id));
    }

    @Transactional
    public void saveBoard(BoardRequest boardRequest) {
        Account account = JwtTokenUtil.getCurrentUser();
        Board board = boardRequest.toEntity();
        board.setAccountId(account.getId());
        boardDao.insert(board);
    }

    @Transactional
    public void updateBoard(Long id, BoardRequest boardRequest) {
        Account account = JwtTokenUtil.getCurrentUser();
        Board board = boardDao.selectOne(id);
        if (board == null) {
            throw new NotFoundException("해당 게시글은 존재하지 않습니다.", 1000);
        }

        if (!account.getId().equals(board.getAccount().getId())) {
            throw new BizException("해당 게시물을 수정할 권한이 없습니다.");
        }

        board.setCategory(boardRequest.getCategory());
        board.setTitle(boardRequest.getTitle());
        board.setContent(boardRequest.getContent());
        boardDao.update(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        Account account = JwtTokenUtil.getCurrentUser();
        Board board = boardDao.selectOne(id);
        if (board == null) {
            throw new NotFoundException("해당 게시글은 존재하지 않습니다.", 1000);
        }

        if (!account.getId().equals(board.getAccount().getId())) {
            throw new BizException("해당 게시물을 삭제할 권한이 없습니다.");
        }

        boardDao.delete(board.getId());
    }
}
