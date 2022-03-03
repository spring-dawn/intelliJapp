package com.kh.ajax.web;

import com.kh.ajax.domain.Item;
import com.kh.ajax.domain.ItemDAO;
import com.kh.ajax.domain.ItemDAOImpl;
import com.kh.ajax.web.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/items")
public class ApiItemController {

//  DAO를 싱글톤으로 만들었으니 Impl 쪽에서 겟인스턴스로 가져온다
  private final ItemDAO itemDAO = ItemDAOImpl.getInstance();

//  상품등록(생성). 리퀘스트바디로 아이템 정보값을 Json형태로 받는다.
  @PostMapping("/")
  public ApiResult<Item> save(@RequestBody Item item){

    Item savedItem = itemDAO.save(item);
    ApiResult<Item> result = new ApiResult<>("00", "success", savedItem);

    return result;

  }


//  상품목록
  @ResponseBody
  @GetMapping("/")
//  반환 타입을 제네릭 타입으로 감싸준다는 게 무슨 뜻일까?
  public ApiResult<List<Item>> findAll() {

    List<Item> list = itemDAO.findAll();

    ApiResult<List<Item>> result = null;
    if (list.size() > 0) {
      result = new ApiResult<>("00", "success", list);
    }else{
//      결과값이 없어도 실패한 건 아니다.
      result = new ApiResult<>("02", "success", null);
    }
    return result;
  }

//  상품조회. 상품의 아이디를 받아 출력하고 리스폰스바디 사용.
  @ResponseBody
  @GetMapping("/{id}")
  public ApiResult<Item> findById(@PathVariable Long id){
    Item findedItem = itemDAO.findById(id);

    ApiResult<Item> result = null;
    if (findedItem != null) {
      result = new ApiResult<Item>("00", "succees", findedItem);
    }else{
      result = new ApiResult<Item>("99", "fail", null);
    }
    return result;
  }

//  상품삭제
  @ResponseBody
  @DeleteMapping("/{id}")
  public ApiResult<String> delete(@PathVariable Long id){
    Item deletedItem = itemDAO.delete(id);

    ApiResult<String> result = null;
    if(deletedItem != null){
      result = new ApiResult<>("00","success", deletedItem.getName()+"이(가) 삭제되었습니다.");
    }else {
      result = new ApiResult<>("99","fail", "상품이 존재하지 않습니다.");
    }

    return result;

  }

//  상품수정
  @ResponseBody
  @PatchMapping("/{id}")
//  반환 타입이 Object인 경우 어떤 타입이건 가능하다
  public ApiResult<Object> update(@PathVariable Long id, @RequestBody Item item){
    Item updateBeforeItem = itemDAO.update(id, item);

//    제너릭 타입을 만들면 결과가 어떻게 되든 유연하게 대처할 수 있다..고
    ApiResult<Object> result = null;
    if(updateBeforeItem != null){
      result = new ApiResult<>("00", "success", itemDAO.findById(item.getId()));
    }else{
      result = new ApiResult<>("99", "fail", "해당 상품이 없습니다.");
    }
    return result;
  }


//  빈 생성 후 후처리. 아 이거 초기화구나?
  @PostConstruct
  public void init(){
//    아이템 객체 생성
    Item item = new Item();
//    필드 지정.
    item.setName("키보드1");
    item.setBrand("LG");
    item.setDesc("LG키보드-1");
    item.setPrice(100000L);
//    만든 필드값 합쳐서 데이터 생성...?
    itemDAO.save(item);

//    덮어써서 수정
    item = new Item();
    item.setName("키보드2");
    item.setBrand("LG");
    item.setDesc("LG키보드-2");
    item.setPrice(150000L);
    itemDAO.save(item);

  }


}
