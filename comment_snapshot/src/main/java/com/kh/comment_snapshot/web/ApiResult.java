package com.kh.comment_snapshot.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//제네릭 타입. 세터를 만들지 않으면 불러다 쓸 수 없고 오직 생성자(초기값)로만 값을 줄 수 있다.
public class ApiResult<T> {
    private String rtcd;
    private String rtmsg;
    private T data;

}
