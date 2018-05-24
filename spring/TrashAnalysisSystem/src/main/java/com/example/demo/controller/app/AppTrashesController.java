package com.example.demo.controller.app;

import com.example.demo.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.demo.controller.BaseAuthenicatedController;
import com.example.demo.controller.api.ApiTrashesController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/app/trashes")
public class AppTrashesController extends BaseAuthenicatedController {
    // TODO: Shouldn't use DI on controller
    //     Refactoring with services or helper classes
    @Autowired
    ApiTrashesController api;

    @ModelAttribute(name = "today")
    private LocalDate setTodayDate() {
        return LocalDate.now();
    }

    // Set default trash by
    @ModelAttribute(name = "trash")
    private Trash setTrash() {
        Trash trash = new Trash();
        trash.setDate(LocalDate.now());
        Category category = new Category();
        // Set the category of id=1 be the default trash category
        category.setId(1);
        trash.setCategory(category);
        return trash;
    }

    @GetMapping
    public String index() {
        return "redirect:/app/trashes/index";
    }

    @GetMapping(path = "index")
    public String showIndex() {
        return "trashes/index";
    }

    @GetMapping(path = "list")
    public String showList(Model model) {
        List<Trash> trashes = api.search(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());
        model.addAttribute("trashes", trashes);
        return "trashes/list";
    }

    @GetMapping(path = "new")
    public String _new() {
        return "trashes/new";
    }

    @PostMapping
    public String create(Trash trash) {
        trash.setUser(currentUser());
        api.create(trash);
        return "redirect:/app/trashes/list";
    }

    @DeleteMapping(path = "{id}")
    public String destroy(@PathVariable Integer id) {
        api.delete(id);
        return "redirect:/app/trashes/list";
    }

    @GetMapping(path = "{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        // TODO: Not correct way to do the error handling when
        //     item is not found
        Trash trash = api.show(id)
            .orElseThrow(() -> new RuntimeException("Item not found"));
        model.addAttribute("trash", trash);
        model.addAttribute("id", id);
        return "trashes/edit";
    }

    @RequestMapping(path = "{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public String update(@PathVariable Integer id, Trash trash) {
        api.update(id, trash);
        return "redirect:/app/trashes/list";
    }
}
