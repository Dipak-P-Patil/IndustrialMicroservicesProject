package com.employeemicroservice.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data // Its generated getter/setter/constructor/to-string/equalHashcode/value
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int employeeId;
    private String employeeName;
    private String employeeAddress;
    private long salary;


}
