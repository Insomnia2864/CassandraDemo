package com.example.cassandrademo.controllers.templates;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class SuccessfulLoginController {

    @GetMapping(value = "/success")
    public String getLogin() {
        return "success";
    }

}
