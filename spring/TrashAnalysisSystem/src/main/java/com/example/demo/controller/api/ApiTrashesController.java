package com.example.demo.controller.api;

import com.example.demo.controller.BaseAuthenicatedController;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;

import java.time.LocalDate;

import com.example.demo.domain.Category;
import com.example.demo.domain.Trash;
import com.example.demo.domain.Trash_;
import com.example.demo.repository.TrashRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/trashes")
public class ApiTrashesController extends BaseAuthenicatedController {
    @Autowired
    private TrashRepository trashRepository;
    @Autowired
    private EntityManager entityManager;

    // GET /:id
    @GetMapping(path = "{id}")
    public Optional<Trash> show(@PathVariable("id") Integer id) {
        return trashRepository.findById(id);
    }

    // POST /
    @PostMapping
    public Trash create(@RequestBody Trash trash) {
        // Using auto generated trash id
        // Associate to current user (which is not in fields)
        trash.setUser(currentUser());
        return trashRepository.save(trash);
    }

    // PUT /:id
    // PATCH /:id
    @RequestMapping(path = "{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Trash update(@PathVariable("id") Integer id,
                        @RequestBody Trash trash) {
        // Insert the id into trash (which is not in fields)
        trash.setId(id);
        // Associate to current user (which is not in fields, too)
        trash.setUser(currentUser());
        return trashRepository.save(trash);
    }

    // DELETE /:id
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Integer id) {
        trashRepository.delete(id);
    }

    // Search Methods
    //

    /**  Filter of index
     *
     *   year := find records matches year
     *   month := find records matches month
     *   day := find records matches day
     *   category := find records match category
     *   all := all users or current user
     */

    // TODO: Ugly. Need Refactoring
    @GetMapping
    public List<Trash> search(@RequestParam("year") Optional<Integer> year,
                              @RequestParam("month") Optional<Integer> month,
                              @RequestParam("day") Optional<Integer> day,
                              @RequestParam("category") Optional<Integer> category,
                              @RequestParam("all") Optional<Boolean> all) {
        // Specify the start date using default value 1-1-1
        LocalDate startDate = LocalDate.of(
            year.orElse(1),
            month.orElse(1),
            day.orElse(1)
        );
        // Specify the end date using default value 9999-12-31
        LocalDate endDate = LocalDate.of(
            year.orElse(9999),
            month.orElse(12),
            day.orElse(1)
        );
        if (!day.isPresent()) {
            endDate = endDate.withDayOfMonth(endDate.lengthOfMonth());
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Trash> cq = cb.createQuery(Trash.class);
        Root<Trash> from = cq.from(Trash.class);
        List<Predicate> conditions = new ArrayList<>();

        if (year.isPresent() || month.isPresent() || day.isPresent()) {
            conditions.add(cb.between(from.get(Trash_.date), startDate, endDate));
        }

        if (category.isPresent()) {
            Category c = new Category();
            c.setId(category.get());
            conditions.add(cb.equal(from.get(Trash_.category), c));
        }

        if (!all.orElse(false).booleanValue()) {
            conditions.add(cb.equal(from.get(Trash_.user), currentUser()));
        }

        return entityManager.createQuery(cq
            .select(from)
            .where(conditions.toArray(new Predicate[] {}))
            .orderBy(cb.desc(from.get(Trash_.date)))
        ).getResultList();
    }

    /**
     *  Calculate the average of weight of trashes
     */
    @GetMapping(path = "average")
    public Double averageWeight(@RequestParam("year") Optional<Integer> year,
                                @RequestParam("month") Optional<Integer> month,
                                @RequestParam("day") Optional<Integer> day,
                                @RequestParam("category") Optional<Integer> category,
                                @RequestParam("all") Optional<Boolean> all) {
        return search(year, month, day, category, all)
            .stream()
            .map(Trash::getWeight)
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0.0);
    }

    /**
     *  Calculate the average of weight of trashes
     */
    @GetMapping(path = "sum")
    public Double sumWeight(@RequestParam("year") Optional<Integer> year,
                            @RequestParam("month") Optional<Integer> month,
                            @RequestParam("day") Optional<Integer> day,
                            @RequestParam("category") Optional<Integer> category,
                            @RequestParam("all") Optional<Boolean> all) {
        return search(year, month, day, category, all)
            .stream()
            .map(Trash::getWeight)
            .mapToDouble(Double::doubleValue)
            .sum();
    }
}
