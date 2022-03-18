package com.kh.app3_snapshot.domain.common.code;

import lombok.Data;

@Data
public class CodeAll {
  private String pcode;         //상위 코드
  private String pdecode;       //상위 디코드
  private String ccode;         //하위 코드
  private String cdecode;       //하위 디코드

}
