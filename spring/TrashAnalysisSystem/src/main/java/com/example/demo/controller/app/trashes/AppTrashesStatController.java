package com.example.demo.controller.app.trashes;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/app/trashes/stat")
public class AppTrashesStatController {
    // @GetMapping(path = "graph")
    // public String showStatGraph(Model model) {
    //     Calendar calendar = Calendar.getInstance();
    //     return showStatGraph(calendar.get(Calendar.YEAR), model);
    // }
}
