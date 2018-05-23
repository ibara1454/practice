package com.example.demo.controller.api;

import java.util.List;
import java.util.Optional;

import java.time.LocalDate;

import com.example.demo.domain.SimpleLoginUser;
import com.example.demo.domain.Trash;
import com.example.demo.repository.TrashRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/trashes")
public class ApiTrashesController {
    @Autowired
    private TrashRepository trashRepository;

    @GetMapping
    public List<Trash> index() {
        return trashRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public Optional<Trash> show(@PathVariable("id") Integer id) {
        return trashRepository.findById(id);
    }

    @PostMapping
    public Trash create(@RequestBody Trash trash) {
        return trashRepository.save(trash);
    }

    @RequestMapping(path = "{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Trash update(@PathVariable("id") Integer id, @RequestBody Trash trash) {
        return trashRepository.save(trash);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Integer id) {
        trashRepository.delete(id);
    }

    // @GetMapping(path = "user")
    // public List<Trash> indexUser(@AuthenticationPrincipal SimpleLoginUser user) {
    //     return trashRepository.findByUser(user.getUser());
    // }

    @GetMapping(path = "date/{date}")
    public List<Trash> findByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return trashRepository.findByDate(date);
    }

    // Search Methods
    //

    @GetMapping(path = "category/{category}")
    public List<Trash> findByCategory(@PathVariable("category") Integer category) {
        return trashRepository.findByCategoryId(category);
    }

    @GetMapping(path = "year/{year}/month/{month}")
    public List<Trash> findByYearMonth(@PathVariable("year") Integer year,
                                   @PathVariable("month") Integer month) {
        LocalDate current = LocalDate.of(year, month, 1);
        return trashRepository.findByDateBetween(current, current.plusMonths(1).minusDays(1));
    }

    @GetMapping(path = "year/{year}/month/{month}/category/{category}")
    public List<Trash> findByYearMonthCategory(@PathVariable("year") Integer year,
                                   @PathVariable("month") Integer month,
                                   @PathVariable("category") Integer category) {
        LocalDate current = LocalDate.of(year, month, 1);
        return trashRepository.findByDateBetweenAndCategoryId(current, current.plusMonths(1).minusDays(1), category);
    }

    @GetMapping(path = "average/year/{year}/month/{month}/category/{category}")
    public Double averageOfFindByYearMonthCategory(@PathVariable("year") Integer year,
                                                   @PathVariable("month") Integer month,
                                                   @PathVariable("category") Integer category) {
        // TODO: Have bad performence. Should refactoring with doing calculation of average
        // in database
        return findByYearMonthCategory(year, month, category)
            .stream()
            .mapToDouble(Trash::getWeight)
            .average().orElse(0.0);
    }
}
