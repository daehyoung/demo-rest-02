package com.example.demorest.service;

import java.util.List;

import com.example.demorest.mapper.ArticleMapper;
import com.example.demorest.vo.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService{
    
    @Autowired
    ArticleMapper articleMapper;

    public ArticleService(){}

    public List<Article> getArticles(){
        return articleMapper.getArticles();
    }

}
