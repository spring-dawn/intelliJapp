package com.kh.product.domain.dao;

import com.kh.product.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class ProductDAOImplTest {
//  디펜던시 인젝션 필요. assertion 은 assertJ 패키지에서 사용할 것.
  @Autowired
  private ProductDAO productDAO;

  @Test
  @DisplayName("상품 등록")
  void saveProduct() {
//    먼저... 빈 상품 객체를 생성하고, 필요한 속성을 부여하고..
    Product pro = new Product();
    pro.setPname("L파일");
    pro.setPquantity(30L);
    pro.setPprice(1000);

//    메소드를 참조해서 매개값으로 넣으면 아이디가 리턴 될 테니까?
    Long savedProId = productDAO.saveProduct(pro);

//    리턴값이 내 예상 id와 일치하는지 확인하면 되겠지
    Assertions.assertThat(savedProId).isEqualTo(5);
    log.info("savedProId={}", savedProId);

  }

  @Test
  @DisplayName("상품 조회")
  void findProductById() {
//    제일 먼저 뭐해야하지? 찾을 상품 id가 필요하지?
    Long proId = 1L;
//    그냥 메소드 넣고 돌리면 됨?
    Product foundItem = productDAO.findProductById(proId);
//    리턴이 프로덕트 타입이니까 검증도 그렇게 해야 하는데. 내용 로그도 좀 찍어보고
    Assertions.assertThat(foundItem.getPname()).isEqualTo("노트");
    log.info("foundItem={}", foundItem);

  }

  @Test
  @DisplayName("상품 수정")
  void updateProduct() {
//    이번에도 매개값으로 입력할 아이디랑 수정할 상품이 필요하다
    Long itemId = 2L;
    Product itemBeforeUpdate = productDAO.findProductById(itemId);
//    대상이 준비됐으면 내용을 바꿔야지. setter 사용.
    itemBeforeUpdate.setPname("파일");
    itemBeforeUpdate.setPquantity(60L);
    itemBeforeUpdate.setPprice(1500);
//    세팅이 됐으면 수정 메소드의 매개값으로 넣어서 수정 작업을 진행한다
//    수정 완료된 횟수, 건수가 리턴값
    int i = productDAO.updateProduct(itemId, itemBeforeUpdate);
    Assertions.assertThat(i).isEqualTo(1);
    log.info("i={}", i);
  }

  @Test
  @DisplayName("상품 삭제")
  void deleteProductById() {
//    삭제할 대상부터 지정
    Long targetItem = 2L;
    int i = productDAO.deleteProductById(targetItem);

    Assertions.assertThat(i).isEqualTo(1);

  }

  @Test
  @DisplayName("전체 품목 조회")
  void findAll() {
    List<Product> items = productDAO.findAll();

    Assertions.assertThat(items.size()).isEqualTo(1);
//    반복문 안 돌려도 다 나오는구나
    log.info("items={}", items);
  }

//  테스트 완료


}