package com.example.demo.common;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import java.util.List;
import java.util.ArrayList;

// 페이징 처리를 위한 클래스
public class PageMaker {

    // [prev][1][2][3][4][5][6][7][8][9][10][next]

    // Example - 게시글 222개, 페이지당 10개의 게시물 출력할 경우

    // Case 1 : 현재 페이지가 3일 경우, startPage = 1, endPage = 10, prev = false, next = true
    // [1][2][(3)][4][5][6][7][8][9][10][next]

    // Case 2 : 현재 페이지가 10일 경우, startPage = 1, endPage = 10, prev = false, next = true
    // [1][2][3][4][5][6][7][8][9][(10)][next]

    // Case 3 : 현재 페이지가 11일 경우, startPage = 11, endPage = 20, prev = true, next = true
    // [prev][(11)][12][13][14][15][16][17][18][19][20][next]

    // Case 4 : 현재 페이지가 21일 경우, startPage = 21, endPage = 23, prev = true, next = false
    // [prev][(21)][22][23]

    private int total; // 전체 게시글의 갯수
    private int start;  // 시작페이지 번호     [1]
    private int end;    // 끝페이지 번호       [10]
    private boolean prev;   // 이전               [prev]
    private boolean next;   // 다음               [next]

    private int pageSize = 10;    // 목록페이지에 보여지는 페이지의 기본갯수 [1][2][3][4][5][6][7][8][9][10]

    private PageSetting pageSetting;          // 페이징 처리를 위한 기준 클래스

    public void setTotal(int total) {
        this.total = total;
        calcData();
    }

    private void calcData() {

        // 끝페이지 연산
        // 끝페이지 = Math.ceil(현재 페이지 번호 / 페이지의 갯수) * 페이지의 갯수
        end = (int) (Math.ceil(pageSetting.getPage() / (double) pageSize) * pageSize);

        // 시작페이지 연산
        // 시작페이지 = (끝페이지 - 페이지의 갯수) + 1
        start = (end - pageSize) + 1;

        // 끝페이지의 재연산 : 만약 게시글 전체 갯수가 222개라면 끝페이지는 30이아니라 23이어야한다.
        // Math.ceil(전체게시글의 갯수 / 페이지당 게시글의 갯수)
        int endPage = (int) (Math.ceil(total / (double) pageSetting.getRowSize()));

        // 끝페이지 연산 결과가 재연산 결과보다 크면
        if (end > endPage) {
            end = endPage;
        }

        // 이전버튼 : 비활성화(시작페이지가 1이면 O) / 활성화(시작페이지가 1이 아니면 X)
        prev = start == 1 ? false : true;

        // 다음버튼 : 비활성화((마지막페이지 * 페이지당 게시글)연산의 결과가 전체 게시글의 갯수보다 작으면) /활성화 (많으면)
        next = end * pageSetting.getRowSize() >= total ? false : true;
    }

    // 쿼리 작성을 위한 메서드
    public String makeQuery(int page) {
        // URI 작성에 도움을 주는 클래스
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page", page)   // 현재페이지
                .queryParam("perPageNum", pageSetting.getRowSize()) // 페이지당 게시글의 갯수
                .build();

        return uriComponents.toUriString();
    }

    // 검색 처리를 위한 메서드
    public String makeSearch(int page) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("rowSize", pageSetting.getRowSize())
                .queryParam("key", ((FilterSetting) pageSetting).getKey())
                .queryParam("value", encoding(((FilterSetting) pageSetting).getValue()))
                .build();

        return uriComponents.toUriString();
    }

    private String encoding(String keyword) {
        if (keyword == null || keyword.trim().length() == 0) {
            return "";
        }
        try {
            return URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public List<Link> getIndex(){
        List<Link> index = new ArrayList<Link>();
        if(prev){
            index.add(new Link("prev",""+(start-1)));
        }
        for(int i=start; i < end+1;++i){
            index.add(new Link(""+i,""+i));
        }

        if(next){
            index.add(new Link("next",""+(end+1)));
        }

        return index;
    }

    public int getTotal() {
        return total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageSetting getPageSetting() {
        return pageSetting;
    }

    public void setCriteria(PageSetting pageSetting) {
        this.pageSetting = pageSetting;
    }

    @Override
    public String toString() {
        return "PageMaker{" +
                "total:" + total +
                ", start:" + start +
                ", end:" + end +
                ", prev:" + prev +
                ", next:" + next +
                ", pageSize:" + pageSize +
                ", pageSetting:" + pageSetting +
                '}';
    }
}