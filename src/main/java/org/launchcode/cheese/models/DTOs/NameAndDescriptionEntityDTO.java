package org.launchcode.cheese.models.DTOs;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
abstract class NameAndDescriptionEntityDTO extends NameEntityDTO {
  @NotNull
  @Size(min = 3, max = 50, message = "description must be between 3 and 20 characters")
  @Pattern(regexp = "[A-Za-z ]+", message = "description must only have alphabetic characters")
  private String description;
}
