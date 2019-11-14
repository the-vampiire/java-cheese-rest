package org.launchcode.cheese.controllers;

import org.launchcode.cheese.models.Category;
import org.launchcode.cheese.models.DTOs.CategoryDTO;
import org.launchcode.cheese.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = CategoryController.ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {
  public static final String ENDPOINT = "/categories";

  @Autowired
  private CategoryRepository categoryRepository;

  @GetMapping
  public ResponseEntity getCategories() {
    return ResponseEntity.ok(categoryRepository.findAll());
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createCategory(@RequestBody @Valid CategoryDTO categoryDTO, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body("invalid bits");
    }

    Category category = categoryDTO.convertToEntity();
    categoryRepository.save(category);

    return ResponseEntity.ok(category);
  }
}

