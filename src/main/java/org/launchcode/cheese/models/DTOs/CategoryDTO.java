package org.launchcode.cheese.models.DTOs;

import org.launchcode.cheese.models.Category;

public class CategoryDTO extends NameEntityDTO implements Convertible<Category> {
  @Override
  public Category convertToEntity() {
    Category category = new Category();
    category.setName(this.getName());

    return category;
  }
}
