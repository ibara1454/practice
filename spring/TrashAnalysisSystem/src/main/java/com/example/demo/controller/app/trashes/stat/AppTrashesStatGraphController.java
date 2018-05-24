package com.example.demo.controller.app.trashes.stat;

import java.time.LocalDate;
import java.util.*;

import com.example.demo.domain.Trash;
import com.example.demo.controller.api.ApiTrashesController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/app/trashes/stat/graph")
public class AppTrashesStatGraphController {
    @Autowired
    private ApiTrashesController api;

    @GetMapping
    public String showStatGraph(@RequestParam("year") Optional<Integer> year, Model model) {
        // LocalDate now = LocalDate.now();
        // List<Trash> trashes = api.search(
        //     Optional.of(year.orElse(now.getYear())),
        //     Optional.empty(),
        //     Optional.empty(),
        //     Optional.empty(),
        //     Optional.empty());
        // model.addAttribute("trashes", trashes);
        return "trashes/stat/graph";
    }
}
