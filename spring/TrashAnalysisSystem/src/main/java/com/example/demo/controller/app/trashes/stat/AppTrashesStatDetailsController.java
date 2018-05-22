package com.example.demo.controller.app.trashes.stat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/app/trashes/stat/details")
public class AppTrashesStatDetailsController {
    @GetMapping
    public String showStatDetails(@RequestParam("year") Integer year,
                                  @RequestParam("month") Integer month,
                                  Model model) {
        return "trashes/stat/details";
    }
}
