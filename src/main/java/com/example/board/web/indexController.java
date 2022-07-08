package com.example.board.web;


import com.example.board.config.auth.LoginUser;
import com.example.board.config.auth.dto.SessionUser;
import com.example.board.service.user.UserService;
import com.example.board.service.board.BoardService;
import com.example.board.web.dto.board.BoardListResponseDto;
import com.example.board.web.dto.board.BoardResponseDto;
import com.example.board.web.dto.UserListResponseDto;
import com.example.board.web.dto.comment.CommentListResponseDto;
import com.example.board.web.dto.comment.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class indexController {

    private final BoardService boardService;
    private final UserService userService;
    private final HttpSession httpSession;

    // Main Index
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
//        String userName= user==null?"":user.getName();
        if(user!=null) {
            model.addAttribute("userName", user.getName());
        }
        System.out.println(httpSession.getAttribute("user"));
        return "index";
    }

    // Board Index
    @GetMapping("/board/save")
    public String boardSave(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        String userName= user==null?"":user.getName();
        model.addAttribute("userName", user.getName());
        return "/board/boardForm";
    }

    @GetMapping("/boardList")
    public String indexBoardList(Model model, @PageableDefault(size=5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
                                ,@RequestParam(required = false, defaultValue = "") String select
                                ,@LoginUser SessionUser user
                                ,@RequestParam(required=false, defaultValue = "") String searchText){
        System.out.println("SearchText : " + searchText);
        Page<BoardListResponseDto> boardList = boardService.findBoardList(searchText, select, pageable);

        int startPage = Math.max(1, boardList.getPageable().getPageNumber()-4);
        int endPage = Math.min(boardList.getTotalPages(), boardList.getPageable().getPageNumber()+4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boardList", boardList);
        String userName= user==null?"":user.getName();
        model.addAttribute("userName", user.getName());

        return "board/boardList";
    }
    @GetMapping("/boardDetail/{id}")
    public String boardDetail(@PathVariable Long id, Model model, @LoginUser SessionUser user){
        BoardResponseDto board = boardService.findById(id);
        List<CommentListResponseDto> commentList = board.getComments();
        System.out.println("댓글 갯수 : " + commentList.size());
        if(commentList != null && !commentList.isEmpty()){
            model.addAttribute("commentList",commentList);
        }
        String userName= user==null?"":user.getName();
        model.addAttribute("userName", userName);
        model.addAttribute("board", board);
        model.addAttribute("user", user);
        return "/board/boardUpdate";
    }

    // Member Index
    @GetMapping("/userList")
    public String getUserList(Model model, @PageableDefault(size=2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
                                ,@LoginUser SessionUser user
                                ,@RequestParam(required = false, defaultValue = "") String select
                                ,@RequestParam(required = false, defaultValue = "") String searchText){
        System.out.println("선택 : " + select + '\n'+ "검색 : " + searchText);
        Page<UserListResponseDto> userList = userService.findUserList(select, searchText, pageable);


        int startPage = Math.max(1, userList.getPageable().getPageNumber()-4);
        int endPage = Math.min(userList.getTotalPages(), userList.getPageable().getPageNumber()+4);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("userList",userList);
        String userName= user==null?"":user.getName();
        model.addAttribute("userName", user.getName());

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
