package com.example.demorest.mapper;

import java.util.List;

import com.example.demorest.vo.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleMapper {
    //@Select("SELECT * FROM ARTICLES WHERE id = #{id}")
    //Article getArticle(@Param("id") Long id);

    //@Select("SELECT * FROM ARTICLES")
    List<Article> getArticles( );
}