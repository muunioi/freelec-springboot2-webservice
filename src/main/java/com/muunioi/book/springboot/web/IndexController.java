package com.muunioi.book.springboot.web;

import com.muunioi.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    //글 등록(posts/save)에 해당하는 컨트롤러 매핑
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }


}
