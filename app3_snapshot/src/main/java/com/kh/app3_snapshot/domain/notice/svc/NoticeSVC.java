package com.kh.app3_snapshot.domain.notice.svc;

import com.kh.app3_snapshot.domain.notice.Notice;
import java.util.List;

//서비스 레이어
public interface NoticeSVC {

  //  등록
  Notice write(Notice notice);

  //  전체조회
  List<Notice> findAll();

  //  상세
  Notice findByNoticeId(Long noticeId);

  //  수정
  Notice modify(Notice notice);

  //  삭제(된 레코드 수=int 반환)
  int remove(Long noticeId);

  //  조회수 증가
  int increaseHit(Long noticeId);

}
