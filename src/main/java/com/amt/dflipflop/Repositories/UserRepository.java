/**
 * Date de création     : 16.10.2021
 * Dernier contributeur : Ryan Sauge
 * Groupe               : AMT-D-Flip-Flop
 * Description          : Tester la connexion avec tomcat
 * Remarque             : -
 * Sources :
 * https://www.baeldung.com/spring-boot-testresttemplate
 */

package com.amt.dflipflop.Repositories;
import com.amt.dflipflop.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/*
Source : https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}
