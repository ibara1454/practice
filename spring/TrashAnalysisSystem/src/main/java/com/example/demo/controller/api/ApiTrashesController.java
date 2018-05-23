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
import org.springframework.format.annotation.DateTimeFormat;
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

    // GET /date/:date
    @GetMapping(path = "date/{date}")
    public List<Trash> findByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return trashRepository.findByDate(date);
    }

    // GET /category/:category
    @GetMapping(path = "category/{category}")
    public List<Trash> findByCategory(@PathVariable("category") Integer category) {
        return trashRepository.findByCategoryId(category);
    }

    // GET /year/:year/month/:month
    @GetMapping(path = "year/{year}/month/{month}")
    public List<Trash> findByYearMonth(@PathVariable("year") Integer year,
                                       @PathVariable("month") Integer month) {
        LocalDate beginDate = LocalDate.of(year, month, 1);
        LocalDate endDate = beginDate.plusMonths(1).minusDays(1);
        return trashRepository.findByDateBetween(beginDate, endDate);
    }

    // GET /year/:year/month/:month/category/:category
    @GetMapping(path = "year/{year}/month/{month}/category/{category}")
    public List<Trash> findByYearMonthCategory(@PathVariable("year") Integer year,
                                               @PathVariable("month") Integer month,
                                               @PathVariable("category") Integer category) {
        LocalDate beginDate = LocalDate.of(year, month, 1);
        LocalDate endDate = beginDate.plusMonths(1).minusDays(1);
        return trashRepository.findByDateBetweenAndCategoryId(beginDate, endDate, category);
    }

    // GET /average/year/:year/month/:month/category/:category
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

    /**
     * year := find records matches year
     * month := find records matches month
     * day := find records matches day
     * category := find records match category
     * all := all users or current user
     */
    @GetMapping
    public List<Trash> search(@RequestParam("year") Optional<Integer> year,
                              @RequestParam("month") Optional<Integer> month,
                              @RequestParam("day") Optional<Integer> day,
                              @RequestParam("category") Optional<Integer> category,
                              @RequestParam("all") Optional<Boolean> all) {

        System.out.println("=========1========");
        // Specify the start date using default value 1-1-1
        LocalDate startDate = LocalDate.of(
            year.orElse(1),
            month.orElse(1),
            day.orElse(1)
        );
        // TODO: fix the invalide date
        // Specify the end date using default value 9999-12-31
        LocalDate endDate = LocalDate.of(
            year.orElse(9999),
            month.orElse(12),
            day.orElse(31)
        );
        System.out.println(startDate);
        System.out.println(endDate);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Trash> cq = cb.createQuery(Trash.class);
        Root<Trash> from = cq.from(Trash.class);
        List<Predicate> conditions = new ArrayList<>();

        System.out.println("=========2========");

        conditions.add(cb.between(from.get(Trash_.date), startDate, endDate));

        if (category.isPresent()) {
            Category c = new Category();
            c.setId(category.get());
            conditions.add(cb.equal(from.get(Trash_.category), c));
        }

        System.out.println("=========3========");

        if (!all.orElse(false).booleanValue()) {
            System.out.println("only current user");
            conditions.add(cb.equal(from.get(Trash_.user), currentUser()));
        }

        System.out.println("=========4========");

        return entityManager.createQuery(cq
            .select(from)
            .where(conditions.toArray(new Predicate[] {}))
        ).getResultList();
    }
}
