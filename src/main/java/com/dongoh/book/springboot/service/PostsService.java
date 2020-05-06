package com.dongoh.book.springboot.service;

import com.dongoh.book.springboot.domain.posts.Posts;
import com.dongoh.book.springboot.domain.posts.PostsRepository;
import com.dongoh.book.springboot.web.dto.PostsListResponseDto;
import com.dongoh.book.springboot.web.dto.PostsResponseDto;
import com.dongoh.book.springboot.web.dto.PostsSaveRequestDto;
import com.dongoh.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor//생성자로 빈을 주입받음
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id:" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id)
    {
        Posts posts=postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다. id="+id));
        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id)
    {
        Posts entity=postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id"+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)//조회기능만 남겨두어 조회속도가 개선됨
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.
                findAllDesc().
                stream().
                map(PostsListResponseDto::new).//map(Posts->new PostsListResponseDto(posts))
                collect(Collectors.toList());
    }
}
