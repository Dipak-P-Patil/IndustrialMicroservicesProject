package com.employeemicroservice.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@SpringBootTest
class DemoApplicationTests {

	@Test
   public void createEmployee() throws URISyntaxException {
		RestTemplate restTemplate=new RestTemplate();
		String baseURL="http://localhost:8080/createEmployee";
		URI uri=new URI(baseURL);
		Employee employee=new Employee();
		employee.setEmployeeAddress("Jalgaon");
		ResponseEntity<String> result=restTemplate.postForEntity(uri,employee,String.class);
		Assertions.assertEquals(201,result.getStatusCodeValue());
	}

}
