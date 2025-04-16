package com.example.javasecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")

public class HelloController {

    @GetMapping("/public")
        public String getPublic() {
            return "hello public";
    }

    @GetMapping("/private")
        private String getPrivate(){
            return "hello private";
    }
}

