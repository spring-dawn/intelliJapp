package com.kh.product.web;

import com.kh.product.domain.Product;
import com.kh.product.domain.svc.ProductSVC;
import com.kh.product.web.form.AddForm;
import com.kh.product.web.form.DetailForm;
import com.kh.product.web.form.EditForm;
import com.kh.product.web.form.ListForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

  private final ProductSVC productSVC;
//  하... 이렇게 만들라는 거였나... ajax 하고 있었는ㄷㅔ
//  메소드를 restAPI랑 완전히 혼동하고 있었네 시간 다 까먹은 주범

//  등록 양식.
  @GetMapping("/add")
  public String addForm(Model model){
//    model로 폼객체를 받아온다. addAtt의 매개값은 (매개변수를 가리킬 이름, 매개변수).
//    한 개 등록할 때마다 새 addForm 객체가 사용되니까 new로 생성해준다
    AddForm addForm = new AddForm();
    model.addAttribute("addForm", addForm);

    return "addForm";
  }

//  등록 처리
  @PostMapping("/add")
  public String add(@ModelAttribute AddForm addForm,
                    RedirectAttributes redirectAttributes){
//    모델어트리뷰트로 필요한 폼의 입력값을 받는다.
//    폼 입력값을 처리할 때는 리턴이 아니라 상세조회 페이지로 리다이렉트 요청을 넣는다
//    리다이렉트 할 거니까 리다이렉트어트리뷰트로 필요한 정보를 날라준다. 맞나?

//    우선 새 상품이 될 product 타입의 새 객체를 만들고 폼 객체의 내용을 옮겨준다.
//    빈유틸.카피프로퍼티즈의 매개값은 (폼객체=사용자 입력값, 폼객체 복사를 받을 변수). 후자가 리턴값인 셈.
    Product product = new Product();
    BeanUtils.copyProperties(addForm, product);

//    입력값을 담았으면 등록 메소드에 넣고 id를 뽑는다
    Long productId = productSVC.saveProduct(product);
//    상세페이지로 가는 데 파라미터로 id가 필요하니 리다이렉트어트리뷰트에 담는다
    redirectAttributes.addAttribute("id", productId);
    return "redirect:/product/{id}/detail";
  }

//  상세 = 개별 조회
//  경로 중 매개값 id를 받을 패스배리어블이랑 데이터를 날라올 모델이 필요
//  패스배리어블에 그냥 Long id를 넣어도 정상작동 하는데 id 이름을 따로 주지 않으면 500에러 발생.
  @GetMapping("/{id}/detail")
  public String detail(@PathVariable("id") Long productId,
                       Model model){
//    입력 받을 건 없고 id~pprice까지 표시해준다 근데 뭐부터 해야하지 일단 메소드를 써야하는데
    Product itemDetail = productSVC.findProductById(productId);

//    디테일폼을 새로 생성하고 가져온 객체 내용을 복사해준다...는 작업이 바로바로 떠올리기가 어렵네;
    DetailForm detailForm = new DetailForm();
    BeanUtils.copyProperties(itemDetail, detailForm);

//    모델에 디테일 정보를 넣고 페이지 리턴하기
    model.addAttribute("detailForm", detailForm);
    return "detailForm";
  }

//  수정 양식
  @GetMapping("/{id}/edit")
  public String editForm(@PathVariable("id") Long productId,
                                   Model model){
//    여태 길 어떻게 찾아갔냐? 입력 받은 id로 수정할 상품에 접근.
    Product foundItem = productSVC.findProductById(productId);
//    수정폼 소환
//    새 객체를 만들면 id도 새로 만들어지는데 어떡하나 했더니, 새 객체에 기존 id를 똑같이 덮어쓰는 거였구나.
//    문자열 타입이랑 비슷하네 기존 객체 변경이 안 된다는 점이
    EditForm editForm = new EditForm();
//    변경 전 내용을 모델 객체에 복사해서 양식에 리턴.
    BeanUtils.copyProperties(foundItem, editForm);
    model.addAttribute(editForm);

    return "editForm";
  }

//  일반 컨트롤러는 겟 포스트 둘만 사용하는 걸 빨리 좀 기억해냈으면 이 고생을 안 했을걸...
//  수정 처리(상세로 리다이렉트)
//  경로상 id 패스배리어블, 폼객체 모델속성, 리다이렉트속성... 바인딩리저트는 오류 찾기용이라 보류.
  @PostMapping("/{id}/edit")
  public String edit(@PathVariable("id") Long productId,
                     @ModelAttribute EditForm editForm,
                     RedirectAttributes redirectAttributes){
//    새 객체
    Product product = new Product();
//    양식의 내용(입력값)을 상품 객체에 복사해주고, 업데이트 메소드에 넣고 수정한다.
    BeanUtils.copyProperties(editForm, product);
    productSVC.updateProduct(productId, product);

//   상세로 리다이렉트 하기 위한 상품 id.
    redirectAttributes.addAttribute("id", productId);
    return "redirect:/product/{id}/detail";
  }

//  삭제. rest api 말고는 delete 못 쓰던가...? 자꾸 405뜨더라니 미안하다 내가 나빴다
  @GetMapping("/{id}/del")
  public String deleteProduct(@PathVariable("id") Long productId){
//    리턴값이 필요한 데가 없으면 안 받아도 되는 거였군
    productSVC.deleteProductById(productId);

      return "redirect:/product";
  }

//  전체 조회. ajax로 처리하려 했는데 설마 웹사이트를 만들어야 했을 줄이야;
  @GetMapping
  public String findall(Model model){
//    객체'들'이다. 컬렉션
    List<Product> items = productSVC.findAll();
    List<ListForm> list = new ArrayList<>();
    
//    iter까지 쓰고 tab. 순회로 목록 출력
    for (Product product : items) {
//      리스트 폼 소환, 폼 양식에 맞춰 필요한 부분만 복사하고 컬렉션에 담는다.
      ListForm listForm = new ListForm();
      BeanUtils.copyProperties(product, listForm);
      list.add(listForm);
    }
//    완성된 목록을 모델에 담아 출력
    model.addAttribute("list", list);
    return "productList";
  }


}
