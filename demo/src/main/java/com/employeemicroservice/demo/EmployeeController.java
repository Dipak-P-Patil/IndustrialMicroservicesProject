package com.employeemicroservice.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    EmployeeRepository empRepository;

    @PostMapping("/createEmployee")
    public  ResponseEntity<String> createEmployee(@RequestBody Employee employee)
    {
        empRepository.save(employee);
        CacheOperation.cache.put(employee.getEmployeeId(),employee);
        return new ResponseEntity<String>("Employee Save Successfully...",HttpStatus.CREATED);

    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee)
    {
        Optional<Employee> oldEmployee = empRepository.findById(employee.getEmployeeId());
        //Optional is avoid null pointer exception.

        if (!oldEmployee.isPresent())
        {
           return ResponseEntity.accepted().body("This Id is Not Present In DB");
            //return ResponseEntity.notFound();
        } else
        {
            return new ResponseEntity<>(empRepository.save(employee), HttpStatus.ACCEPTED);
        }
    }
    @GetMapping("getEmployee/{id}")
    public Employee getEmployeeById(@PathVariable int id)
    {
      // return empRepository.findById(id);
        return CacheOperation.cache.get(id);
    }

    @GetMapping("getAllEmployee")
    public List<Employee> getAllEmployee()
    {
        //return empRepository.findAll();
        return new ArrayList<>(CacheOperation.cache.values());
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployeeById(@PathVariable int id)
    {

        if(empRepository.findById(id).isPresent())
        {

            empRepository.deleteById(id);
            return "This Employee Deleted Successfully";
        }
        else
        {
            return "Employee Id Not Found";

        }
    }

}



















