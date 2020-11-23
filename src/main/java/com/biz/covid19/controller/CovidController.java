package com.biz.covid19.controller;

import com.biz.covid19.service.CovidService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "/")
public class CovidController {

    @Qualifier(value = "covidServiceImpl")
    private final CovidService covidService;

    public CovidController(CovidService covidService) {
        this.covidService = covidService;
    }

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("COVID_LIST", covidService.getCovidList());
        return "home";
    }
}
