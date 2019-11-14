package org.launchcode.cheese.controllers;

import org.launchcode.cheese.models.DTOs.MenuDTO;
import org.launchcode.cheese.models.Menu;
import org.launchcode.cheese.repositories.MenuRepository;
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
@RequestMapping(value = MenuController.ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
  public static final String ENDPOINT = "/menus";

  @Autowired
  MenuRepository menuRepository;

  @GetMapping
  public ResponseEntity getMenus() {
    return ResponseEntity.ok(menuRepository.findAll());
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createMenu(@RequestBody @Valid MenuDTO menuDTO, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body("bad bits");
    }

    Menu menu = menuDTO.convertToEntity();

    menuRepository.save(menu);
    return ResponseEntity.ok(menu);
  }
}
