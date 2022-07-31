package com.jojoldu.webservice.domain.posts;

import com.jojoldu.webservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // Lombok 라이브러리의 어노테이션, 기본 생성자 자동 추가. access = AccessLevl.PROTECTED로 기본생성자의 접근 권한을 protected로 제한한다. = protected Posts() {}
// Entity 클래스를 프로젝트 코드상에서 기본생성자로 생성하는 것을 막되, JPA에서 Entity 클래스를 생성하는 것은 허용하기 위해 사용한다.!!

@Getter // Lombok 라이브러리의 어노테이션, 클래스내 모든 필드의 Getter 메소드 자동 생성
@Entity // 실제 DB의 테이블과 링크되는 클래스. JPA를 사용할 때 쿼리보다는 해당 Entity 클래스를 통해 수정한다.
        // 대문자가 아니라 _으로 이름을 매칭한다.
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 PK 필드. 웬만하면 Entity의 PK는 Long타입 Auto_increment를 추천(MySQL 기준으론 bigint타입)
    @GeneratedValue // PK 생성규칙. 기본값은 AUTO로, MySQL의 auto_increment와 같이 자동증가하는 정수형 값. 스프링 부트 2.0에선 옵션을 추가해야만 auto_increment가 된다.
    private Long id;

    // @Column: 테이블의 컬럼을 나타내면 굳이 해당 어노테이션을 선언하지 않아도 해당 클래스의 필드는 모두 컬럼이 된다. 사용하는 이유는 기본값 외에 추가로 변경이 필요할 때 사용한다.
    @Column(length = 500, nullable = false) // 문자열의 경우 VARCHAR(255)가 기본값인데 사이즈를 500으로 늘림.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // VARCHAR 말고 타입을 TEXT로 변경
    private String content;

    private String author;

    @Builder // Lombok 라이브러리의 어노테이션, 해당 클래스의 빌더패턴 클래스 생성.
    // 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함된다. (title, content, author)
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
