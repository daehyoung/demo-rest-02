package com.example.demo.common;

public class SortSetting extends PageSetting {

    private String key;  // 검색 유형
    private String value;     // 검색 키워드

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SortSetting{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
