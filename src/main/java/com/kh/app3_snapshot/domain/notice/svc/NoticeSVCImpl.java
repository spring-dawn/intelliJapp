package com.kh.app3_snapshot.domain.notice.svc;

import com.kh.app3_snapshot.domain.notice.Notice;
import com.kh.app3_snapshot.domain.notice.dao.NoticeDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service       //서비스 레이어
@RequiredArgsConstructor
public class NoticeSVCImpl implements NoticeSVC {
//    SVC는 사용자의 입력값을 받으면 컨트롤러를 통해 DAO에 전달해주는 역할
    private final NoticeDAO noticeDAO;

    /**
     * 등록
     * @param notice
     * @return
     */
    @Override
    public Notice write(Notice notice) {
        return noticeDAO.create(notice);
    }

    /**
     * 전체조회
     * @return
     */
    @Override
    public List<Notice> findAll() {
        return noticeDAO.selectAll();
    }

    /**
     * 상세조회
     * @param noticeId 공지사항 번호(시퀀스)
     * @return 공지사항 본문 상세페이지 
     */
    @Override
    public Notice findByNoticeId(Long noticeId) {
        return noticeDAO.selectOne(noticeId);
    }

    /**
     * 공지사항 수정
     * @param notice
     * @return
     */
    @Override
    public Notice modify(Notice notice) {
        return noticeDAO.update(notice);
    }

    /**
     * 공지사항 삭제
     * @param noticeId 공지사항 번호(시퀀스)
     * @return
     */
    @Override
    public int remove(Long noticeId) {
        return noticeDAO.delete(noticeId);
    }

    /**
     * 조회 수 증가
     * @param noticeId
     * @return
     */
    @Override
    public int increaseHit(Long noticeId) {
        return noticeDAO.updateHit(noticeId);
    }
}
