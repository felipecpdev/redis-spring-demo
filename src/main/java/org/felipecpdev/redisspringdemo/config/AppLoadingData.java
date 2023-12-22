package org.felipecpdev.redisspringdemo.config;

import jakarta.annotation.PostConstruct;
import org.felipecpdev.redisspringdemo.models.Employee;
import org.felipecpdev.redisspringdemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class AppLoadingData {

    private final EmployeeRepository employeeRepository;

    public AppLoadingData(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    private void loadingData() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = Employee.builder().id(1).employeeNo("123").firstName("Felipe").lastName("Contreras").job("Backend").edLevel("6").workDept("IT").salary(new BigDecimal(2000)).birthDate(new Date()).sex("M").commission(12421_323.0).bonus(233.0).build();
        Employee employee2 = Employee.builder().id(2).employeeNo("456").firstName("Pablo").lastName("Contreras").job("Backend").edLevel("6").workDept("IT").salary(new BigDecimal(2000)).birthDate(new Date()).sex("M").commission(12421_323.0).bonus(233.0).build();

        employees.add(employee);
        employees.add(employee2);

        employeeRepository.saveAll(employees);

    }
}
