package com.example.javasecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageHelloController {

    @GetMapping ("/pagehello")
    public String getPageHello() {
        return "pagehello";
    }
}
