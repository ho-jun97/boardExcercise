package com.example.board.service.comment;

import com.example.board.domain.board.Board;
import com.example.board.domain.board.BoardRepository;
import com.example.board.domain.comment.Comment;
import com.example.board.domain.comment.CommentRepository;
import com.example.board.domain.user.User;
import com.example.board.domain.user.UserRepository;
import com.example.board.web.dto.comment.CommentResponseDto;
import com.example.board.web.dto.comment.CommentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long commentSave(Long boardId, Long userId, CommentSaveRequestDto requestDto){
        // 유저, 보드 조회
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("해당 유저가 없습니다."));
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 없습니다."));

        // 각 entity 정보 저장
        Comment comment = requestDto.toEntity();
        user.addComment(comment);
        board.addComment(comment);

        // 레포에 저장
        Long commentId = commentRepository.save(comment).getId();

        return commentId;
    }

    public CommentResponseDto getComment(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당 댓글이 없습니다."));

        return new CommentResponseDto(comment);
    }
}
