package com.example.board.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    @Query("SELECT u FROM User u ORDER BY u.id DESC")
    Page<User> findAllDesc(Pageable pageable);

    Page<User> findByNicknameContaining(String nickname, Pageable pageable);

    Page<User> findById(Long id, Pageable pageable);
}
