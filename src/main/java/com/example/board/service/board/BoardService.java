package com.example.board.service.board;

import com.example.board.config.auth.PrincipalDetails;
import com.example.board.domain.board.Board;
import com.example.board.domain.board.BoardRepository;
import com.example.board.domain.user.User;
import com.example.board.web.dto.board.BoardListResponseDto;
import com.example.board.web.dto.board.BoardResponseDto;
import com.example.board.web.dto.board.BoardSaveRequestDto;
import com.example.board.web.dto.board.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(User user, BoardSaveRequestDto requestDto){
        Board board = requestDto.toEntity();
        user.addBoard(board);
        return boardRepository.save(board).getId();
    }

    @Transactional
    public void deleteBoard(Long id){
        Board board = boardRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        boardRepository.delete(board);
    }
    @Transactional(readOnly = true)
    public Page<BoardListResponseDto> findBoardList(String searchText, String select,Pageable pageable){
        Page<Board> list = null;
        if(select.equals("제목")){
            list = boardRepository.findByTitleContaining(searchText,pageable);
        }else if(select.equals("저자")){
            list = boardRepository.findByAuthorDesc(searchText,pageable);
        }else{
            list = boardRepository.findAllDesc(pageable);
        }
        Page<BoardListResponseDto> boardDtoList = list.map(BoardListResponseDto::new);

        return boardDtoList;
    }
    public BoardResponseDto findById(Long id){
        Board board = boardRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new BoardResponseDto(board);
    }
    @Transactional
    public Long update(Long id, BoardUpdateRequestDto requestDto){
        Board board = boardRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        System.out.println("Service Update 진행중()");
        board.update(requestDto.getTitle(), requestDto.getContent());

        System.out.println(board.toString());
        return id;
    }
}
