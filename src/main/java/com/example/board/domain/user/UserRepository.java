package com.example.board.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u ORDER BY u.id DESC")
    Page<User> findAllDesc(Pageable pageable);

    Page<User> findByNameContaining(String nickname, Pageable pageable);

    Page<User> findById(Long id, Pageable pageable);
}
