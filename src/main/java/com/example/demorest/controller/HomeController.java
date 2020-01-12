package com.example.demorest.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 

@RestController
public class HomeController {
    @RequestMapping("/")
    public String index(){
        return "hello";
    }
}