package com.rogiss.project.area42.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @RequestMapping({"/", ""})
    public String dashboardView() {
        return "secure/dashboard";
    }

}
