package umc.study.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestErrorController {

    @GetMapping("/test-error")
    public void testError() {
        try {
            throw new RuntimeException("discord 테스트용 에러 발생");
        } catch (RuntimeException e) {
            log.error("💣 테스트용 예외 발생!", e); // Discord로 전송됨
            throw e;
        }
    }
}