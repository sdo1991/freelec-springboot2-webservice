package com.dongoh.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long>
//<Entity클래스, PK>
//기본적인 CRUD 메소드가 자동으로 생성됨
//Entity클래스와 Entity Repository는 함께 위치하여야 함.
{
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")//SpringDataJpa에서 지원하지 않는 메소드를 작성하기 위해 사용함
    List<Posts> findAllDesc();
}
