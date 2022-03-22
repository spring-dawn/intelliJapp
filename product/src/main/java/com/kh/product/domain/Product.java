package com.kh.product.domain;

import lombok.Data;

@Data
public class Product {
//  필드 정의. 가격 단위가 그래도 Long 이 나오겠냐... 하는 마음가짐으로.
  private Long productId;
  private String pname;
  private Long pquantity;
  private int pprice;
//                                              PRODUCT_ID	NUMBER
//                                              PNAME	VARCHAR2(70 BYTE)
//                                              PQUANTITY	NUMBER
//                                              PPRICE	NUMBER

}
