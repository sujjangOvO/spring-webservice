package com.jojoldu.webservice.web;

import com.jojoldu.webservice.domain.posts.PostsRepository;
import com.jojoldu.webservice.dto.PostsSaveRequestDto;
import com.jojoldu.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor // 추가, 모든 필드를 인자값으로 하는 생성자를 Lombok 라이브러리의 @AllArgsConstructor 어노테이션이 대신 생성해준다.
                    // 생성자를 직접 안 쓰고 Lombok 어노테이션을 사용한 이유는 해당 클래스의 의존성 관게가 변경될 때 마다 생성자 코드를 계속해서 수정해야 하는 번거로움을 해결하기 위함.
public class WebRestController {

    private PostsRepository postsRepository;  // 추가
    private PostsService postsService;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto){
        // postsRepository.save(dto.toEntity());
        return postsService.save(dto);
    }
}
