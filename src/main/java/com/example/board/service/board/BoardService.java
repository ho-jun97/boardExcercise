package com.example.board.service.board;

import com.example.board.domain.board.BoardRepository;
import com.example.board.web.dto.BoardSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto requestDto){
        return boardRepository.save(requestDto.toEntity()).getId();
    }
}
