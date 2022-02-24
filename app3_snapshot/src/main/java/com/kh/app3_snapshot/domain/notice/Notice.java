package com.kh.app3_snapshot.domain.notice;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notice {
//  엔터티 객체?
//  데이터 나르기 객체. Data Transfer Object DTO. 이런 거였구나...는 멤버 필드잖아.
//  DB에서 정해둔 타입을 퍼와서 자바와 자료 타입을 맞춰준다.

  private Long noticeId;          //공지 아이디. NOTICE_ID	NUMBER(8,0)
  private String subject;         //SUBJECT VARCHAR2(100BYTE)
  private String content;         //CONTENT CLOB
  private String author;          //AUTHOR VARCHAR2(12BYTE)
  private Long hit;               //HIT NUMBER(5,0)
  private LocalDateTime cdate;    //생성일시. CDATE TIMESTAMP(6)
  private LocalDateTime udate;    //수정일시 UDATE TIMESTAMP(6)


}