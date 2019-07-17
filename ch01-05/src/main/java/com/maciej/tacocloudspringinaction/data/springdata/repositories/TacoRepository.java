package com.maciej.tacocloudspringinaction.data.springdata.repositories;

import com.maciej.tacocloudspringinaction.data.springdata.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
