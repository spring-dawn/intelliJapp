package com.kh.app3_snapshot.domain.bbs.svc;

import com.kh.app3_snapshot.domain.bbs.dao.Bbs;
import com.kh.app3_snapshot.domain.bbs.dao.BbsDAO;
import com.kh.app3_snapshot.domain.common.file.svc.UploadFileSVC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BbsSVCImpl implements BbsSVC {

  private final BbsDAO bbsDAO;
  private final UploadFileSVC uploadFileSVC;

  //private String CODE = "F0101";

  //원글
  @Override
  public Long saveOrigin(Bbs bbs) {
    return bbsDAO.saveOrigin(bbs);
  }

  //원글-첨부파일
  @Override
  public Long saveOrigin(Bbs bbs, List<MultipartFile> files) {

    //1)원글 저장
    Long bbsId = saveOrigin(bbs);

    //2)첨부 저장
    uploadFileSVC.addFile(bbs.getBcategory(),bbsId,files);

    return bbsId;
  }

  //목록
  @Override
  public List<Bbs> findAll() {
    return bbsDAO.findAll();
  }

  //상세조회
  @Override
  public Bbs findByBbsId(Long id) {
    Bbs findedItem = bbsDAO.findByBbsId(id);
    bbsDAO.increaseHitCount(id);
    return findedItem;
  }

  //삭제
  @Override
  public int deleteByBbsId(Long id) {
    //1)첨부파일 삭제
    String bcategory = bbsDAO.findByBbsId(id).getBcategory();
    uploadFileSVC.deleteFileByCodeWithRid(bcategory, id);

    //2)게시글 삭제
    int affectedRow =  bbsDAO.deleteByBbsId(id);

    return affectedRow;
  }

  //수정
  @Override
  public int updateByBbsId(Long id, Bbs bbs) {
    return bbsDAO.updateByBbsId(id, bbs);
  }

  //수정-첨부파일
  @Override
  public int updateByBbsId(Long id, Bbs bbs, List<MultipartFile> files) {

    //1)수정
    int affectedRow = updateByBbsId(id,bbs);

    //2)첨부 저장
    uploadFileSVC.addFile(bbs.getBcategory(),id,files);

    return affectedRow;
  }

  //답글
  @Override
  public Long saveReply(Long pbbsId, Bbs replyBbs) {
    return bbsDAO.saveReply(pbbsId, replyBbs);
  }

  //전체건수
  @Override
  public int totalCount() {
    return bbsDAO.totalCount();
  }
}
