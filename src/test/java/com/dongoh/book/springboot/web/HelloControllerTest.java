package com.dongoh.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
//테스트 진행 시 매개변수(SpringRunner) 스프링 실행자도 실행
//스프링 부트 테스트와 JUnit 사이의 연결자 역할
@WebMvcTest(controllers = HelloController.class)
//WEB에 집중할 수 있는 어노테이션
//@Controller, @ControllerAdvice 등 사용가능
//@Service, @Component, @Repository 등은 사용 불가
public class HelloControllerTest {

    @Autowired
    //스프링이 관리하는 빈 주입
    private MockMvc mvc;
    //웹 API 테스트시 사용
    //스프링 MVC 테스트의 시작점
    //HTTP GET, POST 등의 api 테스트 가능

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello="hello";
        mvc.perform(get("/hello"))//MockMvc를 이용해 해당 주소로 get 요청, 체이닝 가능
                .andExpect(status().isOk())//mvc.perform의 결과 검증. OK=200
                .andExpect(content().string(hello));//본문 내용 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name="hello";
        int amount=1000;

        mvc.perform(get("/hello/dto").param("name",name).param("amount",String.valueOf(amount)))
                //param:API테스트시 사용될 요청 파라미터. String만 허용이기에 숫자,날짜 등은 문자열로 변경해야함
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
                //jsonPath:JSON응답값을 필드별로 검증가능한 메소드
                //$를 기준으로 필드명 명시

    }

}
