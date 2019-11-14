package org.launchcode.cheese.models.DTOs;

// a contract formed that requires implementation of the method
interface Convertible<EntityType> {
  EntityType convertToEntity();
}
