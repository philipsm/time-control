package com.evan.flp_time_control.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @RequestMapping("index")
    public String testLogin(){

        return "HELLO";
    }

}
