package com.example.demo.controller.app.trashes;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/app/trashes/stat")
public class AppTrashesStatController {
    @GetMapping
    public String redirectToStatGraph() {
        return "redirect:/app/trashes/stat/graph";
    }
}
