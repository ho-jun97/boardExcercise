package com.example.board.domain.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT b FROM Board b ORDER BY b.id DESC")
    Page<Board> findAllDesc(Pageable pageable);

//    @Query("select new com.example.board.web.dto.board.BoardListResponseDto(b.id, b.title, b.author, b.modifiedDate) from Board b order by b.id desc")
//    Page<BoardListResponseDto> findBoards(Pageable pageable);

//    @Query("SELECT b FROM Board b ORDER BY b.id DESC")
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

    Page<Board> findByTitleContaining(String title, Pageable pageable);

    @Query("SELECT b FROM Board b WHERE b.user.name LIKE %:author%")
    Page<Board> findByNameDesc(@Param("author") String name, Pageable pageable);
}
