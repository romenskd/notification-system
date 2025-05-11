package com.github.romenskd.notificationdispatcher.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping
    public void test() {
        System.out.println("test");
    }
}
