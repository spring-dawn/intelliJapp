package com.kh.product.web.form;

import lombok.Data;

@Data
public class DetailForm {
//  뷰에서 보여줄 내용
  private Long productId;
  private String pname;
  private Long pquantity;
  private int pprice;

}
