package com.example.board.web;

import com.example.board.domain.user.User;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final UserService userService;

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }
    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/join")
    public String joinProc(User user){
        User u = userService.save(user);

        System.out.println(u.toString());
        return "redirect:/loginForm";
    }

}
