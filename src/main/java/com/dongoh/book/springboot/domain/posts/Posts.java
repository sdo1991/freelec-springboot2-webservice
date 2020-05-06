package com.dongoh.book.springboot.domain.posts;

import com.dongoh.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
//클래스 내의 모든 필드의 게터 메소드를 자동생성
@NoArgsConstructor
//기본 생성자 자동 추가
//public Posts(){}랑 똑같음

//롬복의 어노테이션

//↑덜중요  ↓중요

@Entity
//JPA의 어노테이션
//테이블과 링크될 클래스임을 나타냄
//카멜케이스 이름을 언더스코어 네이밍으로 매칭하는게 기본값
//ex)SalesManager.java->sales_manager table

//Setter는 만들지 않고 각각 메소드를 만들어 해결.
public class Posts extends BaseTimeEntity {//Entity클래스

    @Id//해당 테이블의 PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //PK생성규칙
    //스프링부트 2.0에서는 GenerationType.IDENTITY옵션을 붙여야 auto_increasement됨
    //1.5버전은 다름
    private Long id;

    @Column(length = 500, nullable = false)
    //테이블의 칼럼을 나타냄. 굳이 선언하지 않더라도 해당 클래스의 필드는 전부 컬럼이 됨
    //사용하는 이유:기본 값 외에 추가로 변경이 필요한 옵션이 있을떄
    //ex)문자열은 VARCHAR(255)가 기본값이지만 사이즈를 500으로 늘리고 싶거나, 타입을 TEXT로 변경하고 싶은 경우에 사용
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private String author;

    @Builder
    //빌더패턴 클래스 생성
    //생성자에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author)
    {
        this.title=title;
        this.content=content;
        this.author=author;
    }

    public void update(String title, String content)
    {
        this.title=title;
        this.content=content;
    }

}
