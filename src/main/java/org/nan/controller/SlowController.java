package org.nan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlowController {
    @GetMapping("/slow")
    public String slowRequest() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }

        return "Done";
    }
}
