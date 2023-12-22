package org.felipecpdev.redisspringdemo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.felipecpdev.redisspringdemo.dto.EmployeeDTO;
import org.felipecpdev.redisspringdemo.exceptions.EmployeeExceptionNotFound;
import org.felipecpdev.redisspringdemo.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("emp")
    public ResponseEntity<Object> findEmployeeByNo(@RequestParam String empNo) {
        log.info("GET-- employee by emp-no {}", empNo);
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(employeeService.findByEmployeeNo(empNo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new EmployeeExceptionNotFound("Employee NOT_FOUND"));
        }
    }

    @GetMapping("emp/all")
    public ResponseEntity<Object> getAllEmployeeByNo(@RequestParam String empNo) {
        log.info("GET-- all employee");
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(employeeService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new EmployeeExceptionNotFound("Unexpected error!"));
        }
    }

    @PostMapping("emp/{empNo}")
    public ResponseEntity<Object> updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable String empNo) {
        log.info("UPDATE--  employee with req: {} ", employeeDTO);
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(employeeService.updateEmployee(employeeDTO, empNo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new EmployeeExceptionNotFound("Unexpected error!"));
        }
    }

    @DeleteMapping("emp/{empNo}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable String empNo) {
        log.info("DELETE--  employee no {} ", empNo);
        try {
            employeeService.delete(empNo);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(" ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new EmployeeExceptionNotFound("Unexpected error!"));
        }
    }


}
