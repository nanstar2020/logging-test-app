package org.nan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlowController {
    @GetMapping("/slow")
    public String slowRequest() {
        long end = System.nanoTime() + 2 * 1000 * 1000 * 1000L;
        do {
            for (int i = 0; i < 1000; i++) {
                int j = 100;
                j = j * 1;
            }
        } while(System.nanoTime() < end);

        return "Done";
    }
}
