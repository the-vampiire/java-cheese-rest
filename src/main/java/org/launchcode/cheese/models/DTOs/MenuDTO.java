package org.launchcode.cheese.models.DTOs;

import org.launchcode.cheese.models.Menu;

public class MenuDTO extends NameAndDescriptionEntityDTO implements Convertible<Menu> {
  @Override
  public Menu convertToEntity() {
    Menu menu = new Menu();
    menu.setName(this.getName());
    menu.setDescription(this.getDescription());

    return menu;
  }
}
