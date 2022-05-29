package com.kh.myApp3.web;

import com.kh.myApp3.domain.chart.Chart;
import com.kh.myApp3.domain.chart.ChartDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/chart")
@RequiredArgsConstructor
public class chartController {
    private final ChartDAO chartDAO;

//    기본 페이지 + 데이터 전송
    @GetMapping
    public String chart(){

        return "/chart/chart";
    }

    @GetMapping("/chartAPI")
    @ResponseBody
    public List<Chart> chartAPI(Model model){
        List<Chart> bar = chartDAO.bar();
        model.addAttribute("bar", bar);
        return bar;
    }


}
