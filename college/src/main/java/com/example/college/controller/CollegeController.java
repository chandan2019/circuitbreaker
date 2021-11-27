package com.example.college.controller;

import java.util.Map;

import org.apache.logging.log4j.util.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.college.service.CollegeService;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.control.Try;

@RestController
public class CollegeController {
	
	@Autowired
	private CollegeService collegeService;
	
	@Autowired
	CircuitBreaker timeCircuitBreaker;
	
	@RequestMapping(value = "/college/student/{collegeId}")
	public ResponseEntity<Map> getCollegeStudent(@PathVariable String collegeId){
		
		ResponseEntity<Map> studentByCollege = collegeService.getStudentByCollege(collegeId);
		
		return studentByCollege;
		
/*		Supplier<Map> decoratedSupplier = CircuitBreaker
			    .decorateSupplier(timeCircuitBreaker, collegeService.getStudentByCollege(collegeId));

		ResponseEntity<Map> result = Try.ofSupplier(decoratedSupplier)
			    .recover(throwable -> "Hello from Recovery").get();
		
		String result = timeCircuitBreaker
				  .executeSupplier(collegeService.getStudentByCollege(collegeId));*/
		
		//return new ResponseEntity<Map>(studentByCollege, HttpStatus.OK);
		
	}

}
