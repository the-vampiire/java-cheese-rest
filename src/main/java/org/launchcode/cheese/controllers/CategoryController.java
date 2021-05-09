package org.launchcode.cheese.controllers;

import org.launchcode.cheese.models.Category;
import org.launchcode.cheese.models.Cheese;
import org.launchcode.cheese.models.DTOs.CategoryDTO;
import org.launchcode.cheese.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

  @GetMapping(value = "/{categoryId}")
  public ResponseEntity getCategory(@PathVariable long categoryId) {
    Optional<Category> maybeCategory = categoryRepository.findById(categoryId);

    if (maybeCategory.isPresent()) {
      return ResponseEntity.ok(maybeCategory.get());
    }

    return ResponseEntity.status(404).body("Category not found");
  }

  @GetMapping(value = "/{categoryId}/cheeses")
  public ResponseEntity getCategoryCheeses(@PathVariable long categoryId) {
    Optional<Category> maybeCategory = categoryRepository.findById(categoryId);

    if (!maybeCategory.isPresent()) {
      return ResponseEntity.status(404).body("Category not found");
    }

    // Category was found (is present)
    Category category = maybeCategory.get();
    List<Cheese> cheeses = category.getCheeses();

    return ResponseEntity.ok(cheeses);
  }
}

