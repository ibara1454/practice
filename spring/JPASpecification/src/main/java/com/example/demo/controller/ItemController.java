package com.example.demo.controller;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
  @Autowired
  private ItemService itemService;

  /**
   * GET /items/:id
   * Returns item with specified id.
   * @param  id the id (also the primary key) of item
   * @return    specified customer
   */
  @GetMapping(path = "/{id}")
  public ResponseEntity<?> show(@PathVariable Integer id) {
    return itemService.findOne(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * GET /items?q=keywords&page=0&size=2&sort=id,desc
   * Returns all items which satisfied the searching query.
   * @return List of customer
   */
  @GetMapping
  public Page<Item> findAll(
      @RequestParam(name = "q", defaultValue = "", required = false) String query,
      @PageableDefault(sort = {"id"}, value = 5) Pageable pageable) {
    return itemService.findAll(query, pageable);
  }
}
