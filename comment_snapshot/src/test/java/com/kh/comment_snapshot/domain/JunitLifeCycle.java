package com.kh.comment_snapshot.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

public class JunitLifeCycle {

    @BeforeAll
    static void beforeAll(){
        System.out.println("beforeAll()");
    }

    @BeforeEach



    @Test
    void method1(){
        System.out.println("method()");
    }

    @Test
    void method2(){
        System.out.println("method()");
    }

    @Test
    void method3(){
        System.out.println("method()");
    }


}
