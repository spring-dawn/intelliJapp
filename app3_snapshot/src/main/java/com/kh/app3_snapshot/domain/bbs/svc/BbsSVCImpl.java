package com.kh.app3_snapshot.domain.bbs.svc;

import com.kh.app3_snapshot.domain.bbs.dao.Bbs;
import com.kh.app3_snapshot.domain.bbs.dao.BbsDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BbsSVCImpl implements BbsSVC {

  private final BbsDAO bbsDAO;

//  원글
  @Override
  public Long saveOrigin(Bbs bbs) {
    return bbsDAO.saveOrigin(bbs);
  }

//  목록
  @Override
  public List<Bbs> findAll() {
    return bbsDAO.findAll();
  }

  //  상세조회
  @Override
  public Bbs findByBbsId(Long id) {
    Bbs foundItem = bbsDAO.findByBbsId(id);
    bbsDAO.increaseHitCount(id);
    return foundItem;
  }

//  게시물 삭제
  @Override
  public int deleteByBbsId(Long id) {
    return bbsDAO.deleteByBbsId(id);
  }

//  수정
  @Override
  public int updateByBbsId(Long id, Bbs bbs) {
    return bbsDAO.updateByBbsId(id, bbs);
  }

//  답글
  @Override
  public Long saverReply(Long pbbsId, Bbs replyBbs) {
    return bbsDAO.saverReply(pbbsId, replyBbs);
  }

//  전체건수
  @Override
  public int totalCount() {
    return bbsDAO.totalCount();
  }

}
