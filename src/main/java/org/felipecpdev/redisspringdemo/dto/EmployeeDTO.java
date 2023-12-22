package org.felipecpdev.redisspringdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("employee_no")
    private String employeeNo;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("mid_init")
    private String midInit;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("work_dept")
    private String workDept;
    @JsonProperty("phone_no")
    private String phoneNo;
    @JsonProperty("hire_date")
    private Date hireDate;
    @JsonProperty("job")
    private String job;
    @JsonProperty("ed_level")
    private String edLevel;
    @JsonProperty("sex")
    private String sex;
    @JsonProperty("birth_date")
    private Date birthDate;
    @JsonProperty("salary")
    private BigDecimal salary;
    @JsonProperty("bonus")
    private Double bonus;
    @JsonProperty("commission")
    private Double commission;
}
