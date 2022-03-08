package com.kh.app3_snapshot.domain.covid.svc;

import java.time.LocalDate;

public interface CovidSVC {
   Response getCovidInfo(String startDate, String endDate);
}
