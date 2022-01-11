package com.testcode.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //JSON을 반환하는 컨트롤러로
public class HelloController {

    @GetMapping("/hello") //HTTP GET request 받을 수 있는 API로 만듬
    public String hello(){
        return "hello";
    }
}
