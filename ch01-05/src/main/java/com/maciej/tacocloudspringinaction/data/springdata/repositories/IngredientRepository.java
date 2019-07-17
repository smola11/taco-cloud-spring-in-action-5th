package com.maciej.tacocloudspringinaction.data.springdata.repositories;

import com.maciej.tacocloudspringinaction.data.springdata.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
