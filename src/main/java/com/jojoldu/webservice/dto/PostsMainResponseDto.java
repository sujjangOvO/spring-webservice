package com.jojoldu.webservice.dto;

import com.jojoldu.webservice.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Getter
public class PostsMainResponseDto {
    private Long id;
    private String title;
    private String author;
    private String modifiedDate;  // View영역에선 LocalDateTime 타입을 모르기 때문에 인식할 수 있도록 문자열로 날짜형식을 변경하여 등록한다.

    public PostsMainResponseDto(Posts entity){
        id = entity.getId();
        title = entity.getTitle();
        author = entity.getAuthor();
        modifiedDate = toStringDateTime(entity.getModifiedDate());
    }

    /* java 8 버전*/
    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }

    /* java 7 버전 */
    private String toStringDateTimeByJava7(LocalDateTime localDateTime){
        if(localDateTime == null){
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(localDateTime);
    }

}
