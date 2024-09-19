package com.hard.cegAndranovelona.controleurs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {

    @GetMapping("/loginAdmin")
    public String getLoginAdmin() {
        return "loginAdmin";
    }
    
}

