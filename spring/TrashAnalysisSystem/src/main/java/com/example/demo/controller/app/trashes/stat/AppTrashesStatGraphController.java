package com.example.demo.controller.app.trashes.stat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/app/trashes/stat/graph")
public class AppTrashesStatGraphController {
    @GetMapping
    public String showStatGraph(@RequestParam("year") Integer year, Model model) {
        // Calendar calendar = Calendar.getInstance();
        // return showStatGraph(calendar.get(Calendar.YEAR), model);
        return "trashes/stat/graph";
    }
}
