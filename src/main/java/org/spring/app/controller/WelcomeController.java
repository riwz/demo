package org.spring.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class WelcomeController {
    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }
}
