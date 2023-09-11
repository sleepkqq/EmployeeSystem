package com.sleepkqq.employeesystem.repository;

import com.sleepkqq.employeesystem.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {
}
