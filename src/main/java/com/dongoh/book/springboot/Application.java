package com.dongoh.book.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication//스프링 부트의 자동 설정, 스프링 bean 읽기 생성 자동 설정, 여기서부터 설정을 읽어가므로 프로젝트 최상단에 위치
public class Application {
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);//내장 WAS 실행
    }
}
