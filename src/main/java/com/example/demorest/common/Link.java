package com.example.demo.common;

public class Link{
    String name;
    String uri;

    public Link(){}

    public Link(String name,String uri){
        this.name = name;
        this.uri = uri;
    }
    

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUri(){
        return this.uri;
    }

    public void setUri(String uri){
        this.uri = uri;
    }

    public String toString(){
        return "\""+this.name+"\":\""+this.uri +"\"";
    }
}