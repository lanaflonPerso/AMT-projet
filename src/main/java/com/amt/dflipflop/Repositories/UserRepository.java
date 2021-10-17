package com.amt.dflipflop.Repositories;
import com.amt.dflipflop.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
