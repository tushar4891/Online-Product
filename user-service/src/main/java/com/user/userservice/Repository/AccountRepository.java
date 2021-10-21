package com.user.userservice.Repository;
import java.util.Optional;

import com.user.userservice.Account;
import com.user.userservice.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
   
    // @Query(value = "SELECT p FROM Account p WHERE p.id=?1")
    // public Account  getAccountById(int id);

    // @Query(value = "SELECT p FROM Person, a FROM Account WHERE p.id EQUAL a.person_id AND a.id=?1")
    // public Person getPersonFromAccount(int id);
}
