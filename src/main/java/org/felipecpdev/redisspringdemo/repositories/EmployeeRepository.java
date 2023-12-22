package org.felipecpdev.redisspringdemo.repositories;

import org.felipecpdev.redisspringdemo.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findFirstByEmployeeNo(String empNo);
}
