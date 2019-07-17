package com.maciej.tacocloudspringinaction.data.springdata.repositories;

import com.maciej.tacocloudspringinaction.security.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
