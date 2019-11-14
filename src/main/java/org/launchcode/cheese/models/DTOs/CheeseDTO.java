package org.launchcode.cheese.models.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.launchcode.cheese.models.Cheese;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CheeseDTO extends NameAndDescriptionEntityDTO implements Convertible<Cheese> {
  @NotNull
  @Min(value = 1, message = "invalid category ID")
  private long categoryId;

  @Override
  public Cheese convertToEntity() {
    // we have encapsulated the logic associated with translating a DTO to its corresponding Entity
    // otherwise the Controller is burdened with this logic and must be kept synchronized
    Cheese cheese = new Cheese();
    cheese.setName(this.getName());
    cheese.setDescription(this.getDescription());

    return cheese;
  }
}
