package com.kh.product.domain.dao;

import com.kh.product.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO{

//  jdbc임포트
  private final JdbcTemplate jdbcTemplate;

  /**
   * 상품 등록
   * @param product 필드와 일치하는 상품 정보
   * @return 상품 id
   */
  @Override
  public Long saveProduct(Product product) {
//    1) 상품 객체를 만든다? 그러려면 sql문이 우선 필요하지.. 언제나 db가 우선이다.
    StringBuffer sql = new StringBuffer();
              sql.append(" INSERT ");
              sql.append(" INTO product (  product_id, pname, pquantity, pprice) ");
              sql.append(" VALUES (  product_product_id_seq.nextval, ?, ?, ? ) ");

//     2) sql문이 완성됐으면 jdbcTemplate로 자바와 db를 연결한다.
//     그런데 시퀀스 값은 키홀더가 필요하고, 프리페어드스테이트먼트..로 객체를 만든다
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
            sql.toString(),
            new String[]{"product_id"}
        );
        pstmt.setString(1, product.getPname());
        pstmt.setLong(2, product.getPquantity());
        pstmt.setInt(3, product.getPprice());

        return pstmt;
      }
    }, keyHolder);

//    3) id 리턴. 왜 스트링으로 바꿨다가 롱으로 바꿨다가 두 번이나 캐스팅하는 거지.
    return Long.valueOf(keyHolder.getKeys().get("product_id").toString());
  }

  /**
   * 개별 조회
   * @param id 상품 id
   * @return  상품 정보(객체?)
   */
  @Override
  public Product findProductById(Long id) {
//    조회는 또 어떻게 하냐? 가장 먼저, sql이지 그래
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT product_id, pname,  pquantity, pprice ");
    sql.append(" FROM product ");
    sql.append(" where product_id = ? ");

//    나왔으면 jdbc로 매핑을 해야 하는데, 이건 단일 리턴이니까 쿼리포오브젝트를 쓴다..
//    리턴 타입이 Product 니까 java 필드 정보와 db 컬럼이 일치하도록 매핑한다. 여기선 자동 가능. 매퍼도 new로 생성해야하네;
    Product foundProduct = jdbcTemplate.queryForObject(
        sql.toString(),
        new BeanPropertyRowMapper<>(Product.class),
        id
    );

    return foundProduct;
  }

  /**
   * 수정
   * @param id 상품 id
   * @param product 수정하려는 상품 정보
   * @return
   */
  @Override
  public int updateProduct(Long id, Product product) {
//    sql!!!
    StringBuffer sql = new StringBuffer();
    sql.append(" UPDATE product ");
    sql.append(" SET pname = ?, pquantity = ?, pprice = ? ");
    sql.append(" WHERE product_id = ? ");

//    매핑을 해야 하는데 리턴이 아니라 추가, 변경, 삭제 등은 update 메소드 오버로딩을 사용한다.
    int updatedItemCnt = jdbcTemplate.update(sql.toString(),
        product.getPname(),
        product.getPquantity(),
        product.getPprice(),
        id
    );
    return updatedItemCnt;
  }

  /**
   * 상품 삭제
   * @param id 상품 id
   * @return 삭제 된 건수(횟수)
   */
  @Override
  public int deleteProductById(Long id) {
//    삭제는 비교적 길이가 짧다.
    String sql = " delete from product where product_id = ? ";

//    뭐지 이거 맞는 건가?
    int deletedItemCnt = jdbcTemplate.update(sql, id);

    return deletedItemCnt;
  }

  /**
   * 전체 조회
   * @return 모든 상품 정보
   */
  @Override
  public List<Product> findAll() {
    String sql = " SELECT product_id, pname, pquantity, pprice FROM product order by product_id ";

//    전체 조회=다수 리턴이니까 뭐 쿼리빳다죠
    List<Product> items = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));

    return items;
  }



}
