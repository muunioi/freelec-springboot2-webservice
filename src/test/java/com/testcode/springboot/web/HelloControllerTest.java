package com.testcode.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //SpringBootTest 와 JUnit 사이의 연결자 (SpringRunner라는 실행자)
@WebMvcTest(controllers = HelloController.class) //Web에 집중한 Annotation(@)
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 bean을 주입 받음
    private MockMvc mvc; //Web API 테스트 시에 사용 - HTTP GET, POST 등에 대한 API 테스트 가능

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // /hello로 GET 요청
                .andExpect(status().isOk()) //mvc.perform의 결과 검증 = HTTP header의 Status 검증(200, 404, 500..)
                .andExpect(content().string(hello)); //mvc.perform의 결과 검증 = 응답 본문의 내용 검증
    }
}
