package com.example.board.service.user;

import com.example.board.domain.user.User;
import com.example.board.domain.user.UserRepository;
import com.example.board.web.dto.UserListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public User save(User user){
        user.setRole("ROLE_USER");
        String rawPwd = user.getPassword();
        String encPwd = bCryptPasswordEncoder.encode(rawPwd);
        user.setPassword(encPwd);
        userRepository.save(user);

        return user;
    }
    public Page<UserListResponseDto> findUserList(String select, String searchText, Pageable pageable){
        Page<User> users = null;
        if(select.equals("이름")){
            users = userRepository.findByNicknameContaining(searchText, pageable);
        }else if(select.equals("번호")){
            users = userRepository.findById(Long.parseLong(searchText), pageable);
        }else{
            users = userRepository.findAllDesc(pageable);
        }
        return users.map(UserListResponseDto::new);
    }
}
