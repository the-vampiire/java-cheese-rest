package org.launchcode.cheese.services;

import org.launchcode.cheese.models.Category;
import org.launchcode.cheese.models.Cheese;
import org.launchcode.cheese.models.DTOs.CheeseDTO;
import org.launchcode.cheese.repositories.CategoryRepository;
import org.launchcode.cheese.repositories.CheeseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class CheeseService {
  @Autowired
  // express our need for dependencies during construction
  private CheeseRepository cheeseRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  public Cheese createCheese(CheeseDTO cheeseDTO) {
    Category category = categoryRepository
        .findById(cheeseDTO.getCategoryId())
        .orElseThrow(EntityNotFoundException::new);

    Cheese cheese = cheeseDTO.convertToEntity();
    cheese.setCategory(category);
    category.getCheeses().add(cheese);

    cheeseRepository.save(cheese);

    return cheese;
  }

  public List<Cheese> findAll() {
    return cheeseRepository.findAll();
  }

  public Cheese getCheese(long cheeseId) {
    return cheeseRepository.findById(cheeseId).orElseThrow(EntityNotFoundException::new);
  }
}
