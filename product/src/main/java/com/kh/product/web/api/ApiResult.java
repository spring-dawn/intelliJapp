package com.kh.product.web.api;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//<T> 제네릭 타입
public class ApiResult<T> {
  private String rtcd;        //리턴 코드
  private String rtmsg;       //리턴 메시지
  private T data;             //타입이 자유로운 데이터

}