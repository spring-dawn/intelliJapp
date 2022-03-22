package com.kh.product.domain.svc;

import com.kh.product.domain.Product;

import java.util.List;

public interface ProductSVC {
  //  상품 등록. 필드에 맞는 양식을 입력 받고 만들어진 새 객체 레코드의 id(시퀀스)를 반환한다.
  Long saveProduct(Product product);

  //  상품 개별 조회. id를 입력 받고 상품 정보(=Product)를 반환한다.
  Product findProductById(Long id);

  //  상품 내용 수정. 수정하려는 상품의 id와 객체를 입력 받고 수정된 상품 개수(=1)을 확인차 반환한다.
  int updateProduct(Long id, Product product);

  //  상품 삭제. id로 삭제하고 삭제된 건수 1을 반환.
  int deleteProductById(Long id);

  //  전체 조회. 리턴을 뭘로 받지 보이드? 인트? 다수 리턴이니까 일단 컬렉션 씌워
  List<Product> findAll();

}
