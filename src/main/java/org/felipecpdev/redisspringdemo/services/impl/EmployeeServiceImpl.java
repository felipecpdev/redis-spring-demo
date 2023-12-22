package org.felipecpdev.redisspringdemo.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.felipecpdev.redisspringdemo.dto.EmployeeDTO;
import org.felipecpdev.redisspringdemo.exceptions.EmployeeExceptionNotFound;
import org.felipecpdev.redisspringdemo.models.Employee;
import org.felipecpdev.redisspringdemo.repositories.EmployeeRepository;
import org.felipecpdev.redisspringdemo.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Cacheable("EmployeeDTOList")
    public List<EmployeeDTO> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        if (!employees.isEmpty()) {
            return employees.stream()
                    .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    @Cacheable(value = "EmployeeDTO", key = "#empNo")
    public EmployeeDTO findByEmployeeNo(String empNo) {
        log.info("Fetch employee from db by emp-no {}", empNo);
        Optional<Employee> employee = employeeRepository.findFirstByEmployeeNo(empNo);
        return employee
                .map(empl -> modelMapper
                        .map(empl, EmployeeDTO.class))
                .orElseThrow(() -> new EmployeeExceptionNotFound("Employee not found"));
    }

    // limpia el cache EmployeeDTOList si la operación es exitosa
    @Override
    @Caching(put = {@CachePut(value = "EmployeeDTO", key = "#empNo")},
            evict = {@CacheEvict(value = "EmployeeDTOList", allEntries = true)})
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, String empNo) {
        Optional<Employee> employee = employeeRepository.findFirstByEmployeeNo(empNo);
        if (employee.isPresent()) {
            employee.get().setSex(employeeDTO.getSex());
            employee.get().setEdLevel(employeeDTO.getEdLevel());
            Employee tempEmp = employeeRepository.save(employee.get());
            return modelMapper.map(tempEmp, EmployeeDTO.class);
        }
        log.error("No employee {} found", empNo);
        return null;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "EmployeeDTOList", allEntries = true),
            @CacheEvict(value = "EmployeeDTO", key = "#empNo")
    })
    public void delete(String empNo) {
        Optional<Employee> employee = employeeRepository.findFirstByEmployeeNo(empNo);
        employee.ifPresent(employeeRepository::delete);
    }
}
