package com.microsoft.gamemanager;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    @RequestMapping("/api/version")
    @CrossOrigin(origins="*") // This is bad but I don't have a good way to control it in the wild (Challenge) 
    public String Version() {
        return "1.0.0.0";
    }
}