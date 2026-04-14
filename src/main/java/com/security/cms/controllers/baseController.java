package com.security.cms.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class baseController {
    @GetMapping()
    public String welcome(){
        return "Welcome to Crime station";
    }
}
