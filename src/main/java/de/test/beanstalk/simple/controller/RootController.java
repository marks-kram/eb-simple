package de.test.beanstalk.simple.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @RequestMapping
    public ResponseEntity<String> awsHealthCheck(){
        return ResponseEntity.ok("health: ok");
    }

}
