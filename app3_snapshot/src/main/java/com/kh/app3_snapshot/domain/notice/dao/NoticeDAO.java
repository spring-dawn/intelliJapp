package com.kh.app3_snapshot.domain.notice.dao;

import com.kh.app3_snapshot.domain.notice.Notice;

import java.util.List;

public interface NoticeDAO {

//  등록
 Notice create(Notice notice);

//  전체조회
 List<Notice> selectAll();

//  상세
  Notice selectOne(Long noticeId);

//  수정
  Notice update(Notice notice);

//  삭제(된 레코드 수=int 반환)
  int delete(Long noticeId);

//  조회수 증가
  int updateHit(Long noticeId);



}
