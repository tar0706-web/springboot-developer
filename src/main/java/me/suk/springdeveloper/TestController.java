package me.suk.springdeveloper;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hi")
    public String hi () {
        return "안녕하세요 'http://lcoalhost:8080/hi' 에 대한 응답입니다.";

    }
    @GetMapping("/test")
    public String test () {
        return "안녕하세요 'http://lcoalhost:8080/hi' 에 대한 응답입니다.";
    }
}
