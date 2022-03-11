package com.kh.app3_snapshot.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest     //컨테이너를 사용하면 이것도 필요하다고 한다.
class PersonTest {

//   case 1 필드 주입
    @Autowired      //필드 인젝션. 필드 주입. 아니면 밑에 코드처럼 써야한다.
    Person person;
    //    Person person;
    //      PersonTest(Person person){
    //          this.person = person;
    //      }

    //case 2 생성자 주입(RequiredArgsConstructor)
    //private final Person person;


    @Test
    @DisplayName("DI 컨테이너 미사용")
    void person1(){

        //Person p1 =  new Person("홍길동", 30);
        //log.info(p1.getName());
        //p1.study();
        //p1.smile();
    }

    @Test
    @DisplayName("DI 컨테이너 사용")
    void person2(){
        //p1.smile();

    }



}