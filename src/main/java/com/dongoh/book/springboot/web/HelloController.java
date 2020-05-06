package com.dongoh.book.springboot.web;
import com.dongoh.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController//컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌
public class HelloController {
    @GetMapping("/hello")//GET 리퀘스트 받는 api
    public String hello()
    {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount)
    //@requstParam:외부에서 api 넘긴 파라메터를 가져오는 어노테이션
    {
        return new HelloResponseDto(name,amount);
    }

}
