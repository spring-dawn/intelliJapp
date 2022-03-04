package com.kh.ajax.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
  public String name;
  private Integer age;

  public void setAge(Integer age) {
//   세터게터도 커스터마이징 할 수 있다. alt+insert로 기본 형태 가져와서 재정의.
    if (age < 0) {
      age = 0;
      return;
    }
    this.age = age;
  }
}
