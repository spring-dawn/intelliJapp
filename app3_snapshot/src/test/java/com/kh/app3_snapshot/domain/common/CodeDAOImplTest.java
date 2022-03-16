package com.kh.app3_snapshot.domain.common;

import com.kh.app3_snapshot.web.Code;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CodeDAOImplTest {

  @Autowired
  private CodeDAO codeDAO;

  @Test
  void code() {

    List<Code> codes = codeDAO.code("B01");
    codes.stream().forEach(code -> log.info(code.toString()));

  }

  @Test
  void codeAll() {
    List<CodeAll> codeAlls = codeDAO.codeAll();
    codeAlls.stream().filter(code -> code.getPcode().equals("B01"))
        .forEach(code -> log.info(code.toString()));
  }

  @Test
  void stream(){
    List<Integer> arr = Arrays.asList(1,2,3);
    arr.stream().forEach(ele -> log.info(ele.toString()));
  }



}