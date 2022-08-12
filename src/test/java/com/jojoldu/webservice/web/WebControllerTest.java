package com.jojoldu.webservice.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class WebControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩() {

        //given (테스트 기반 환경 구축)

        //when (테스트 하고자 하는 행위)
        String body = this.restTemplate.getForObject("/",String.class);

        //then (테스트 결과 검증)
        assertThat(body).contains("스프링부트로 시작하는 웹 서비스"); // 전체 코드를 다 찾지 않고 해당 문자열이 포함되어 있는지만 비교

    }
}
