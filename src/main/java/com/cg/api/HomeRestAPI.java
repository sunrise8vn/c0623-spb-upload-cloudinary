package com.cg.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeRestAPI {

    @GetMapping
    public ResponseEntity<?> test() {

        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }
}
