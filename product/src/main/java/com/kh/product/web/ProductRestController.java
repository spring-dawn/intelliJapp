package com.kh.product.web;

import com.kh.product.domain.Product;
import com.kh.product.domain.svc.ProductSVC;
import com.kh.product.web.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController     // 컨트롤러+리스폰스바디. json객체를 클라이언트에게 전송할 수 있다.
@RequiredArgsConstructor
@RequestMapping("/product/api")
public class ProductRestController {

  private final ProductSVC productSVC;

//  등록.
//  @RequestBody 로 데이터를 json형태로 받을 수 있다. 등록된 객체의 정보를 json으로 받아서 apiresult 타입으로 감싸기.
  @PostMapping
  public ApiResult<Product> saveProduct(@RequestBody Product product) {
//    이거 리턴이 id잖아 그럼 어떻게 출력되는 거지. 아이템 정보를 json형태로 받으려면 조회 메소드를 한 번 쓰는 게 맞나.
    Long savedItemId = productSVC.saveProduct(product);
    Product itemDetail = productSVC.findProductById(savedItemId);

//    포스트맨에서 raw 요청하면 정상작동하는데, 왜 다른 폼으로는 메소드 오류가 나지?
    ApiResult<Product> result = new ApiResult<>("00", "success", itemDetail);
    return result;
  }

//  개별 조회
  @GetMapping("/{id}")
  public ApiResult<Product> findById(@PathVariable Long id){
    Product foundItem = productSVC.findProductById(id);

    ApiResult<Product> result = null;
    if( foundItem != null ){
      result = new ApiResult<>("00", "success", foundItem);
    }else{
      result = new ApiResult<>("99", "fail", null);
    }

    return result;
  }

//  수정
  @PatchMapping("/{id}")
  public ApiResult<Object> updateProduct(@PathVariable Long id, @RequestBody Product product){
//    수정하려는 상품의 id와 객체를 매개값으로 넣고 수정횟수를 리턴 받는다
    int updateCnt = productSVC.updateProduct(id, product);
//    수정횟수만으론 정보값이 없으므로 같은 id로 수정된 객체를 얻는다
    Product updatedItem = productSVC.findProductById(id);

    ApiResult<Object> result = null;
    if( updateCnt == 1 ){     //수정이 완료됐으면 해당 상품 정보 리턴.
      result = new ApiResult<>("00", "success", updatedItem);
    }else {
      result = new ApiResult<>("99", "fail", "해당 상품이 없습니다.");
    }
    return result;
  }


//  삭제
  @DeleteMapping("/{id}")
  public ApiResult<String> deleteProduct(@PathVariable Long id){
    Product deletedItem = productSVC.findProductById(id);
    int deleteCnt = productSVC.deleteProductById(id);

    ApiResult<String> result = null;
    if( deleteCnt == 1 ){
      result = new ApiResult<>("00", "success", deletedItem.getPname()+" 이(가) 삭제되었습니다.");
    }else{
      result = new ApiResult<>("99", "fail", "상품을 찾을 수 없습니다.");
    }
    return result;
  }

//  전체 조회
  @GetMapping
  public ApiResult<List<Product>> findAll(){
    List<Product> list = productSVC.findAll();

    ApiResult<List<Product>> result = null;
    if( list.size() > 0){
      result = new ApiResult<>("00", "success", list);
    }else{
      result = new ApiResult<>("99", "fail", list);
    }
    return result;
  }



}
