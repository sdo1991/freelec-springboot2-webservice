package com.dongoh.book.springboot.domain.posts;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    //Junit에서 단위테스트가 끝날때마다 수행되는 메소드
    //테스트간 데이터 침범을 막기 위해 사용
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기()
    {
        //given
        final int LENGTH_MAX=10;
        final int LOOP_MAX=1000;

        String[] title=new String[LOOP_MAX];
        String[] content=new String[LOOP_MAX];
        String[] author=new String[LOOP_MAX];



        for(int i=0;i<LENGTH_MAX;i++) {
            title[i]=RandomStringUtils.random(LENGTH_MAX);
            content[i]=RandomStringUtils.random(LENGTH_MAX);
            author[i]=RandomStringUtils.random(LENGTH_MAX);
            postsRepository.save(Posts.builder().title(title[i]).content(content[i]).author(author[i]).build());
        }
        //posts에 insert/update 쿼리를 실행
        //id값이 있다면 update, 없다면 insert가 실행됨

        //when
        List<Posts> postsList=postsRepository.findAll();
        //posts 테이블에 있는 모든 데이터를 조회

        //then
        for(int i=0;i<LENGTH_MAX;i++) {
            Posts posts = postsList.get(i);
            assertThat(posts.getTitle()).isEqualTo(title[i]);
            assertThat(posts.getContent()).isEqualTo(content[i]);
            assertThat(posts.getAuthor()).isEqualTo(author[i]);
        }
    }
/*
    @Test
    public void 게시글등록_수정()
    {

    }
*/
    @Test
    public void BaseTimeEntity_등록()
    {
        //given
        LocalDateTime now=LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        //when
        List<Posts> postsLists=postsRepository.findAll();

        //then
        Posts posts=postsLists.get(0);

        System.out.println(">>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate().isAfter(now));
        assertThat(posts.getModifiedDate().isAfter(now));
    }


}
