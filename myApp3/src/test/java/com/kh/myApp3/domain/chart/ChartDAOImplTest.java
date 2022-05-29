package com.kh.myApp3.domain.chart;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ChartDAOImplTest {

    @Autowired
    private ChartDAO chartDAO;

    @Test
    @DisplayName("막대 차트 데이터")
    void bar() {
        List<Chart> bar = chartDAO.bar();
        log.info("bar", bar);

    }
}