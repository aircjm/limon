package com.aircjm.limon.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aircjm
 */
@RequestMapping("/ts")
@RestController
public class TsController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
