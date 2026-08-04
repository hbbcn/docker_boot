package com.hbb.docker.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class HelloController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/order/docker")
    public String hello() {
        return "hello docker" + "\t" + port + "\t" + UUID.randomUUID().toString();
    }

    @RequestMapping(value = "/order/index",method = RequestMethod.GET)
    public String index() {
        return "服务端口号" + "\t" + port + "\t" + UUID.randomUUID().toString();
    }



}