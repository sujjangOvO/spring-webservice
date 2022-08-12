package com.jojoldu.webservice.service;

import com.jojoldu.webservice.dto.PostsMainResponseDto;
import com.jojoldu.webservice.domain.posts.PostsRepository;
import com.jojoldu.webservice.dto.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostsService {

    private PostsRepository postsRepository; // DTO

    @Transactional
    public Long save(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId(); // 저장한 게시글의 id를 리턴
    }

    @Transactional(readOnly = true)   // 조회기능만 남겨두어 조회 속도가 개선됨 따라서 등록/수정/삭제 기능이 없는 메소드에선 사용하는것이 좋음
    public List<PostsMainResponseDto> findAllDesc(){
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new)  // .map(posts -> new PostsMainResponseDto(posts))와 같다.
                .collect(Collectors.toList());
    }
}
