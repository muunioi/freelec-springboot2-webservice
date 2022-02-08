package com.muunioi.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    //글 등록(posts/save)에 해당하는 컨트롤러 매핑
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
