package com.kh.app3_snapshot.domain.common.code;

import com.kh.app3_snapshot.web.Code;

import java.util.List;

//공통으로 사용 될 DAO. 글킨 하지 카테고리 분류는 모든 게시판에서 쓸 테니까
public interface CodeDAO {

  /**
   * 상위코드를 입력하면 하위코드를 반환
   * @param pcode 상위코드
   * @return 그 아래 하위코드와 디코드(코드 설명)
   */
  List<Code> code(String pcode);

  /**
   * 모든 코드 반환
   * @return
   */
  List<CodeAll> codeAll();


  
  
  

}
