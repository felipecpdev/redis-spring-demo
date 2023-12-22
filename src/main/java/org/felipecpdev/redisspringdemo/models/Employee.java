package org.felipecpdev.redisspringdemo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String employeeNo;
    private String firstName;
    private String midInit;
    private String lastName;
    private String workDept;
    private String phoneNo;
    private Date hireDate;
    private String job;
    private String edLevel;
    private String sex;
    private Date birthDate;
    private BigDecimal salary;
    private Double bonus;
    private Double commission;

}
