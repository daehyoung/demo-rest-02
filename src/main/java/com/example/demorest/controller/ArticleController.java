package com.example.demorest.controller;

import java.util.List;

import com.example.demorest.vo.Article;

import com.example.demorest.service.ArticleService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class ArticleController {
    
    @Autowired
    ArticleService articleService;

 
 
    @RequestMapping("/articles")
    public @ResponseBody List<Article> demo_test()throws Exception{
        return articleService.getArticles();
    }
    
}