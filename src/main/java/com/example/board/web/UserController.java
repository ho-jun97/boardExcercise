package com.example.board.web;

import com.example.board.domain.user.User;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/memberList")
    public String getMemberList(Model model){
        List<User> memberList = userService.getUsers();
        model.addAttribute("memberList",memberList);

        return "memberList";
    }

}
