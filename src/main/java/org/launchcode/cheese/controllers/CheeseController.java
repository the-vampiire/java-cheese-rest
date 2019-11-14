package org.launchcode.cheese.controllers;

import org.launchcode.cheese.models.Cheese;
import org.launchcode.cheese.models.DTOs.CheeseDTO;
import org.launchcode.cheese.services.CheeseService;
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

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(value = CheeseController.ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public class CheeseController {
  public static final String ENDPOINT = "/cheeses";

  @Autowired
  // express our need for dependencies during construction
  private CheeseService cheeseService;

  // GET /cheeses: get all the cheeses
  @GetMapping
  public ResponseEntity getCheeses() {
    return ResponseEntity.ok(cheeseService.findAll());
  }

  // POST /cheeses: create a new cheese
  // expects: { "name" : "", "description": "" }
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createCheese(@RequestBody @Valid CheeseDTO cheeseDTO, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body("bad stuff");
    }
    try {
      Cheese cheese = cheeseService.createCheese(cheeseDTO);
      return ResponseEntity.ok(cheese);
    // -- RESPONSIBILITIES OF CONTROLLER  ABOVE --//
    } catch (EntityNotFoundException creationError) {
    // -- RESPONSIBILITIES OF CONTROLLER BELOW --//
      // should be using creationError.getMessage() not defining own error message
      // Controller shouldnt be coupled to the detail of creation business
      return ResponseEntity.badRequest().body("Category not found");
    }
  }

  // GET /cheeses/{cheeseId}: get a single cheese by its ID
  // cheeseId should be a path variable
  @GetMapping(value = "/{cheeseId}")
  public ResponseEntity getCheese(@PathVariable long cheeseId) {
    try {
      Cheese cheese = cheeseService.getCheese(cheeseId);
      return ResponseEntity.ok(cheese);
    } catch (EntityNotFoundException cheeseNotFound) {
      return ResponseEntity.status(404).body("Cheese not found");
    }
  }
}
