package com.damirkinapp.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/shareServiceFallBack")
    public String userServiceFallBackMethod() {
        return "Share Service is taking longer than Expected." +
                " Please try again later";
    }

    @GetMapping("/historyServiceFallBack")
    public String departmentServiceFallBackMethod() {
        return "History Service is taking longer than Expected." +
                " Please try again later";
    }
}
