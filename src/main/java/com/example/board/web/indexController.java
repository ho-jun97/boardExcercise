package com.example.board.web;


import com.example.board.config.auth.PrincipalDetails;
import com.example.board.domain.user.User;
import com.example.board.service.user.UserService;
import com.example.board.service.board.BoardService;
import com.example.board.web.dto.board.BoardListResponseDto;
import com.example.board.web.dto.board.BoardResponseDto;
import com.example.board.web.dto.UserListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class indexController {

    private final BoardService boardService;
    private final UserService userService;

    // Main Index
    @GetMapping("/")
    public String index(){
        return "index";
    }

    // Board Index
    @GetMapping("/board/save")
    public String boardSave(){
        return "/board/boardForm";
    }

    @GetMapping("/boardList")
    public String indexBoardList(Model model, @PageableDefault(size=5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                    @RequestParam(required = false, defaultValue = "") String select,
                    @RequestParam(required=false, defaultValue = "") String searchText){
        System.out.println("SearchText : " + searchText);
        Page<BoardListResponseDto> boardList = boardService.findBoardList(searchText, select, pageable);

        int startPage = Math.max(1, boardList.getPageable().getPageNumber()-4);
        int endPage = Math.min(boardList.getTotalPages(), boardList.getPageable().getPageNumber()+4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boardList", boardList);

        return "board/boardList";
    }
    @GetMapping("/boardDetail/{id}")
    public String boardDetail(@PathVariable Long id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        BoardResponseDto board = boardService.findById(id);
        User user = principalDetails.getUser();
        model.addAttribute("board", board);
        model.addAttribute("user", user);
        return "/board/boardUpdate";
    }

    // Member Index
    @GetMapping("/userList")
    public String getUserList(Model model, @PageableDefault(size=2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
                                ,@RequestParam(required = false, defaultValue = "") String select
                                ,@RequestParam(required = false, defaultValue = "") String searchText){
        System.out.println("선택 : " + select + '\n'+ "검색 : " + searchText);
        Page<UserListResponseDto> userList = userService.findUserList(select, searchText, pageable);


        int startPage = Math.max(1, userList.getPageable().getPageNumber()-4);
        int endPage = Math.min(userList.getTotalPages(), userList.getPageable().getPageNumber()+4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("userList",userList);

        return "userList";
    }
    @GetMapping("/loginForm")
    public String loginForm(){
        return "sign/loginForm";
    }
    @GetMapping("/join")
    public String join(){
        return "sign/join";
    }
}
