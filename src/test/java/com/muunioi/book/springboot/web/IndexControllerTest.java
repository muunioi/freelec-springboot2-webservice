package com.muunioi.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩(){

        //when
        //TestRestTemplate 을 통해 "/"로 호출했을 때
        String body = this.restTemplate.getForObject("/", String.class);

        //then
        //index.mustache 에 포함된 코드가 있는지 확인 (정확하게 일치하는 내용이 존재해야지 수행되는 것을 확인할 수 있음/띄어쓰기,오타X)
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
    }
}
