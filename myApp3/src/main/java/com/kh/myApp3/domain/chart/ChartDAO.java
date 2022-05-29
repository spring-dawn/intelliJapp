package com.kh.myApp3.domain.chart;

import java.util.List;

public interface ChartDAO {

    /**
     * 막대 그래프 데이터. 제 기능을 하려면 매개값으로 연도를 받아야한다(21, 22년 등등)
     * @return 해당 연도의 담당자별 업무량.
     */
    List<Chart> bar();

}
