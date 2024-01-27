package com.hideandseak.domain.drink.domain.repository;

import com.hideandseak.domain.drink.domain.DrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkRepository extends JpaRepository<DrinkEntity, Long> {
    List<DrinkEntity> findByCategoryContaining(String category);
}
