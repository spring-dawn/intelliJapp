package com.kh.app3_snapshot.domain.common.paging;

import lombok.Getter;
import lombok.Setter;

//상속
@Getter @Setter
public class FindCriteria extends PageCriteria{
  private String searchType;
  private String keyword;

//자동으로 생성자 만들어주네
  public FindCriteria(RecordCriteria rc, int pageCount) {
    super(rc, pageCount);
  }
}
