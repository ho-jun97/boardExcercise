package com.example.board.service.board;

import com.example.board.domain.board.Board;
import com.example.board.domain.board.BoardRepository;
import com.example.board.web.dto.BoardListResponseDto;
import com.example.board.web.dto.BoardResponseDto;
import com.example.board.web.dto.BoardSaveRequestDto;
import lombok.RequiredArgsConstructor;
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
    public List<BoardListResponseDto> findAllDesc(){
        return boardRepository.findAllDesc().stream()
                .map(BoardListResponseDto::new)
                .collect(Collectors.toList());
    }
    public BoardResponseDto findById(Long id){
        Board board = boardRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new BoardResponseDto(board);
    }
}