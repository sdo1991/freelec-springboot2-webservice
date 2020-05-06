package com.dongoh.book.springboot.web.dto;

import com.dongoh.book.springboot.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    private String content;

    public PostsListResponseDto(Posts entity)
    {
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.author=entity.getAuthor();
        this.modifiedDate=entity.getModifiedDate();

        this.content=entity.getContent();

        if(this.content.length()>7)
            this.content=this.content.substring(7)+".....";

    }

}
