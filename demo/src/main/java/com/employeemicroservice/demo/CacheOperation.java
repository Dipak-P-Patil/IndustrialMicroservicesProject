package com.employeemicroservice.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Component
public class CacheOperation {

    @Autowired
    EmployeeRepository empRepository;

  public static HashMap<Integer,Employee> cache= new HashMap<>();
  public List<Employee> employeeList;

 // @PostConstruct
 // @Scheduled(cron = "${cronExpression}")
  @Scheduled(cron = " 0 */2 * ? * *")
  public void  loadCache(){

      System.out.println("cache load started");

      employeeList=empRepository.findAll();
      if(!employeeList.isEmpty()){

          employeeList.forEach(employee -> cache.put(employee.getEmployeeId(),employee));
      }
      System.out.println("cache load ended");
  }

}
