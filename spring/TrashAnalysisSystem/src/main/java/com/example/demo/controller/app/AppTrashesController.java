package com.example.demo.controller.app;

import com.example.demo.domain.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/app/trashes")
public class AppTrashesController {
    @GetMapping(path = "home")
    public String showHome() {
        return "trashes/home";
    }

    @GetMapping
    public String index() {
        return "trashes/index";
    }

    @GetMapping(path = "new")
    public String _new() {
        return "trashes/new";
    }

    @PostMapping
    public String create(@RequestBody Trash trash) {

        return "redirect:/trashes/index";
    }

    @DeleteMapping(path = "{id}")
    public String destroy(@PathVariable Integer id, Model model) {
        return "redirect:/trashes/index";
    }

    @GetMapping(path = "{:id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        return "trashes/edit";
    }

    @RequestMapping(path = "{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public String update(@PathVariable Integer id, Model model) {
        return "redirect:/trashes/index";
    }
}
