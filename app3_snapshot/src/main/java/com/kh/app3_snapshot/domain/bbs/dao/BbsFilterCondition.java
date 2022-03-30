package com.kh.app3_snapshot.domain.bbs.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

//    검색에 필요한 객체가 너무 많아서 따로 클래스를 만들었다.
import java.util.List;

@Getter
@AllArgsConstructor     //모든 멤버필드의 생성자 생성
@ToString
public class BbsFilterCondition {
  private String category;        //게시판 분류코드
  private int startRec;           //시작레코드 번호
  private int endRec;             //종료레코드 번호
  private String searchType;      //검색유형
  private String keyword;         //검색어

//  인텔리제이 code 메뉴에서 자동 generate 해준다
  public BbsFilterCondition(String category, String searchType, String keyword) {
    this.category = category;
    this.searchType = searchType;
    this.keyword = keyword;
  }
//  불변 객체로 만들어 처음 할당값이 결코 변하지 않게 한다 > setter 를 만들지 않는다.
  


}
