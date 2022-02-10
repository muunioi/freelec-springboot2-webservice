package com.muunioi.book.springboot.web;

import com.muunioi.book.springboot.config.auth.dto.SessionUser;
import com.muunioi.book.springboot.service.posts.PostsService;
import com.muunioi.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        //로그인 성공 시 httpSession에서 유저 attribute에서 값을 가져와 SessionUser에 저장
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        //세션에 저장된 값이 있을 때만 model에 userName으로 등록
       if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    //글 등록(posts/save)에 해당하는 컨트롤러 매핑
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    //업데이트
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
