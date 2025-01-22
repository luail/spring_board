package com.example.board.board.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//logback객체 만드는 방법1
@Slf4j
public class LogController {

//    logback 객체 만드는 방법2
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/log/test")
    public String logTest() {
//        기존의 system print는 실무에서 사용하지 않음.
//        이유1. 성능이 좋지 않음.
//        이유2. 로그분류 작업 불가.
        System.out.println("시스템 프린트 로그");
//        로그레벨 : trace < debug < info < error
        logger.trace("trace 로그 입니다");
        logger.debug("debug 로그 입니다.");
        logger.info("info 로그 입니다.");
        logger.error("error 로그 입니다.");

//        Slf4j 어노테이션을 선언시, log라는 변수로 logback 객체 사용가능.
        log.info("slf4j 테스트입니다.");
        log.error("slf4j error로그 테스트입니다.");
//        error 로그는 에러가 터졌을 때 사용. info는 정보성 로그 출력시 사용. debug는 테스트 목적으로 사용.
        try {
            log.info("에러 테스트 시작.");
            throw new RuntimeException("에러테스트");
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }

        return "OK";
    }
}
