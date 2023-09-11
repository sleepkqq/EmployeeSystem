package com.sleepkqq.employeesystem.repository;

import com.sleepkqq.employeesystem.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    Developer findByEmail(String email);

    Developer findByName(String name);

}
