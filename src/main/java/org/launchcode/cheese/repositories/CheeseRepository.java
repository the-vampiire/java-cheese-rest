package org.launchcode.cheese.repositories;

import org.launchcode.cheese.models.Cheese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional // TODO: when used in a Service use MANDATORY propagation
public interface CheeseRepository extends JpaRepository<Cheese, Long> {}
