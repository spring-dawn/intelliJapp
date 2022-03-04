package com.kh.ajax.web.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResult<T> {
  private String rtcd;    //리턴코드. 서버 처리 상태 코드.
  private String rtmsg;   //리턴메시지. 서버 처리 상태 메시지.
  private T data;         //실제 전송되는 데이터


}
