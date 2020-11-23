package com.biz.covid19.service;

import com.biz.covid19.domain.CovidVO;

import java.util.List;

public interface CovidService {
    List<CovidVO> getCovidList();
}
