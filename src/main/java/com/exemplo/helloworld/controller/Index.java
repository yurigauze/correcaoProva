package com.exemplo.helloworld.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Index {

    @GetMapping("/")
    public String hello() {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return "Olá mundo Spring, hoje é dia " + format.format(new Date());
    }
}
