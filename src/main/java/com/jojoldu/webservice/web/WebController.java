package com.jojoldu.webservice.web;

import com.jojoldu.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;

    @GetMapping("/")   // @RequestMapping(value="/", method = RequestMethod.GET) 과 동일
    public String main(Model model){

        model.addAttribute("posts",postsService.findAllDesc());
        return  "main";   // handlebars-spring-boot-starter 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 path와 뒤의 파일 확장자는 자동으로 지정된다.
        // 예: path = src/main/resources/templates, 확장자 = .hbs
        // src/main/resources/templates 경로의 main.hbs로 전환되어 view Resolver가 처리한다.
    }
}
