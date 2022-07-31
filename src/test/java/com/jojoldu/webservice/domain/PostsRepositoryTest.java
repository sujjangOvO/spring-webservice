package com.jojoldu.webservice.domain;

import com.jojoldu.webservice.domain.posts.Posts;
import com.jojoldu.webservice.domain.posts.PostsRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDateTime;
import java.util.List;


import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach   // Junit 5에서는 헷갈림 방지를 위해 @After가 @AfterEach로 변경되었다.
    public void cleanup(){
        // 이후 테스트 코드에 영향을 미치지 않기 위해 테스트 메소드가 끝날 때 마다 repository를 비우는 코드
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        // given : 테스트 기반 환경을 구축, @Builder 사용법도 같이 확인
        postsRepository.save(Posts.builder()    // 생성자 대신 빌더 사용
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("jojoldu@gamil.com")
                .build());

        // when : 테스트 하고자 하는 행위 선언, Posts가 DB에 insert 되는 것을 확인하기 위함
        List<Posts> postsList = postsRepository.findAll();

        // then : 테스트 결과 검증, 실제로 DB에 insert 되었는지 확인하기 위해 조회 후, 입력된 값 확인
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(),is("테스트 게시글"));
        assertThat(posts.getContent(), is("테스트 본문"));

    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                        .title("테스트 게시글")
                        .content("테스트 본문")
                        .author("jojoldu@gamil.com")
                        .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }

}

