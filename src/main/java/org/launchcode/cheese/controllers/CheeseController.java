package org.launchcode.cheese.controllers;

import org.launchcode.cheese.models.Cheese;
import org.launchcode.cheese.repositories.CheeseRepository;
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
import java.util.Optional;

@RestController
@RequestMapping(value = CheeseController.ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public class CheeseController {
  public static final String ENDPOINT = "/cheeses";

  @Autowired
  // express our need for dependencies during construction
  private CheeseRepository cheeseRepository;

  // GET /cheeses: get all the cheeses
  @GetMapping
  public ResponseEntity getCheeses() {
    return ResponseEntity.ok(cheeseRepository.findAll());
  }

  // POST /cheeses: create a new cheese
  // expects: { "name" : "", "description": "" }
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createCheese(@RequestBody @Valid Cheese cheese, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body("bad stuff");
    }

    cheeseRepository.save(cheese);
    return ResponseEntity.ok(cheese);
  }

  // GET /cheeses/{cheeseId}: get a single cheese by its ID
  // cheeseId should be a path variable
  @GetMapping(value = "/{cheeseId}")
  public ResponseEntity getCheese(@PathVariable long cheeseId) {
    Optional<Cheese> maybeCheese = cheeseRepository.findById(cheeseId);

    if (maybeCheese.isPresent()) {
      return ResponseEntity.ok(maybeCheese.get());
    }

    return ResponseEntity.status(404).body("Cheese not found");
  }
}
