package com.aorun.ymgh.controller.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/worker")
@RestController
public class TestEnvironmentController {

    @Autowired
     private Environment env;

    @RequestMapping("/testProfile")
     public String testProfile(){
        return env.getProperty("profile");
    }

}
