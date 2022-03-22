package com.kh.product.web.form;

import lombok.Data;

@Data
public class EditForm {
//  수정할 대상 id(사용자가 변경할 수는 없음)
  private Long productId;

//  뷰에서 사용자가 변경할 필드
  private String pname;
  private Long pquantity;
  private int pprice;

}
