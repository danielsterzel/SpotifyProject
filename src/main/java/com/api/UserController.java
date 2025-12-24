package com.api;

import org.springframework.web.bind.annotation.GetMapping;


public class UserController {

    @GetMapping("/")
    public String index()
    {
        return "home page";
    }
    @GetMapping("/ping")
    public String ping()
    {
        return "pong";
    }
}
