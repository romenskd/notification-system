package com.github.romenskd.statustracker.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping
    public void test() {
        System.out.printf("helloworld");
    }
}
