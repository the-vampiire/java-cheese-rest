package org.launchcode.cheese.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "categories")
@EqualsAndHashCode
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  // fetch = LAZY means when you grab this Entity (Category) DO NOT grab all cheeses
  // fetch = EAGER means when you grab this Entity (Category) DO grab all cheeses too
  // DEFAULT = LAZY
  @OneToMany(mappedBy = "category")
  private Set<Cheese> cheeses;
}

