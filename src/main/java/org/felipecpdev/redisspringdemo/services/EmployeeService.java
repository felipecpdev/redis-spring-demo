package org.felipecpdev.redisspringdemo.services;

import org.felipecpdev.redisspringdemo.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO findByEmployeeNo(String empNo);

    List<EmployeeDTO> getAll();

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, String empNo);

    void delete(String empNo);
}
