package me.suk.springdeveloper;


import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @GetMapping("/hi")
    public String hi () {
        return "안녕하세요 '안녕하세요? ' 에 대한 응답입니다.";

    }
    @GetMapping("/test")
    public String gtest () {
        return "안녕하세요 '안녕하세요? /' 에 대한 응답입니다.";
    }
    @DeleteMapping("/test")
    public String dtest () {
        return "안녕하세요 '안녕하세요? /dlt' 에 대한 응답입니다.";
    }
    @PutMapping("/test")
    public String puttest () {
        return "안녕하세요 '안녕하세요? /Put' 에 대한 응답입니다.";
    }
    @PostMapping("/test")
    public String posttest () {
        return "안녕하세요 '안녕하세요? /Post' 에 대한 응답입니다.";
    }
}
