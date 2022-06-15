package com.example.board.service.board;

import com.example.board.domain.board.Board;
import com.example.board.domain.board.BoardRepository;
import com.example.board.web.dto.BoardListResponseDto;
import com.example.board.web.dto.BoardResponseDto;
import com.example.board.web.dto.BoardSaveRequestDto;
import com.example.board.web.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto requestDto){
        return boardRepository.save(requestDto.toEntity()).getId();
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
