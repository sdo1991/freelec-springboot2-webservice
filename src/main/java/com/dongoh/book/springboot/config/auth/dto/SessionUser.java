package com.dongoh.book.springboot.config.auth.dto;

import com.dongoh.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {//User엔티티 클래스의 직렬화 DTO
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user)
    {
        this.name=user.getName();
        this.email=user.getEmail();
        this.picture=user.getPicture();
    }
}
