package org.launchcode.cheese.repositories;

import org.launchcode.cheese.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MenuRepository extends JpaRepository<Menu, Long> {}
