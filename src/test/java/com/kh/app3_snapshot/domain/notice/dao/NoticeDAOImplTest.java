package com.kh.app3_snapshot.domain.notice.dao;

import com.kh.app3_snapshot.domain.notice.Notice;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class NoticeDAOImplTest {

  @Autowired  //SC(스프링컨테이너)에서 동일타입의 객체를 주입받는다.
  private NoticeDAO noticeDAO;

  @Test
  @DisplayName("공지사항 등록")
  void create() {

    Notice notice = new Notice();
    notice.setSubject("제목3");
    notice.setContent("본문3");
    notice.setAuthor("홍길동3");
    Notice savedNotice = noticeDAO.create(notice);
    Assertions.assertThat(notice.getSubject()).isEqualTo(savedNotice.getSubject());
    log.info("savedNoticdId={}", savedNotice.getNoticeId());
  }

  @Test
  @DisplayName("공지사항 조회 1건")
  void selectOne() {
    Long noticdId = 27L;
    Notice notice = noticeDAO.selectOne(noticdId);
    Assertions.assertThat(notice).isNotNull();
    log.info("notice={}", notice);
  }

  @Test
  @DisplayName("공지사항 변경")
//  null pointer exception
  void update() {
    Long noticeId = 27L;
    Notice notice = noticeDAO.selectOne(noticeId);
    notice.setSubject("수정 후 제목27");
    notice.setContent("수정 후 본문27");

    Notice updatedNotice = noticeDAO.update(notice);

    Assertions.assertThat(updatedNotice.getSubject()).isEqualTo(notice.getSubject());
    Assertions.assertThat(updatedNotice.getContent()).isEqualTo(notice.getContent());

  }

  @Test
  @DisplayName("공지사항 삭제 by 공지id")
  void delete() {
//    when
    Long noticeId = 26L;

//    try
    int cnt = noticeDAO.delete(noticeId);

//    then
    Assertions.assertThat(cnt).isEqualTo(1);
//    Assertions.assertThat(noticeDAO.selectOne(24L)).isNull();
  }

  @Test
  @DisplayName("공지사항 전체조회")
  void selectAll() {

    List<Notice> notices = noticeDAO.selectAll();

    Assertions.assertThat(notices.size()).isEqualTo(3);
    log.info("notices={}", notices);
  }

  @Test
  @DisplayName("조회 수 증가")
  void increaseHit(){
//    when
    Long noticeId = 28L;
    Notice notice = noticeDAO.selectOne(noticeId);

    Long currentHit = notice.getHit();

//    try
    noticeDAO.updateHit(notice.getNoticeId());

//    then
    Notice noticeAfterHitting = noticeDAO.selectOne(noticeId);
    Assertions.assertThat(noticeAfterHitting.getHit()).isEqualTo(currentHit+1);

  }
}





