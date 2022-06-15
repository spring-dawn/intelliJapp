package com.kh.myApp3.domain.chart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class ChartDAOImpl implements ChartDAO{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Chart> bar() {
        String sql = " select count(item), manager from chart where cdate like '22%' group by manager ";

        List<Chart> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Chart.class));
        return result;    }







}
