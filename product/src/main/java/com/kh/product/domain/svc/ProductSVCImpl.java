package com.kh.product.domain.svc;

import com.kh.product.domain.Product;
import com.kh.product.domain.dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional      //추가 변경 삭제 등의 작업 중 문제가 생기면 작업을 원점으로? 되돌린다. db 보호?
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC{

//  dao에 정의한 그대로 리턴하면 된다.
  private final ProductDAO productDAO;

  /**
   * 등록
   * @param product
   * @return
   */
  @Override
  public Long saveProduct(Product product) {
    return productDAO.saveProduct(product);
  }

  /**
   * 개별 조회
   * @param id
   * @return
   */
  @Override
  public Product findProductById(Long id) {
    return productDAO.findProductById(id);
  }

  /**
   * 수정
   * @param id
   * @param product
   * @return
   */
  @Override
  public int updateProduct(Long id, Product product) {
    return productDAO.updateProduct(id, product);
  }

  /**
   * 삭제
   * @param id
   * @return
   */
  @Override
  public int deleteProductById(Long id) {
    return productDAO.deleteProductById(id);
  }

  /**
   * 전체 조회
   * @return
   */
  @Override
  public List<Product> findAll() {
    return productDAO.findAll();
  }
}
