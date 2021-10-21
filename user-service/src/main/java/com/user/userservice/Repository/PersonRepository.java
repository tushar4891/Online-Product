package com.user.userservice.Repository;

import org.springframework.data.repository.NoRepositoryBean;

import com.user.userservice.Person;

import org.springframework.data.jpa.repository.JpaRepository;


@NoRepositoryBean
public interface PersonRepository extends JpaRepository<Person,Integer> {
    

}
