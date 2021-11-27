package com.example.student.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.example.student.model.Student;

@RestController
public class StudentController {

	
	
	@RequestMapping(value = "/student/{collegeId}", method = RequestMethod.GET)
	
	public ResponseEntity<Map<String, List<Student>>> getAllStudent(@PathVariable String collegeId){
		
		Map<String, List<Student>> map = new HashMap<>();
		List<Student> list = new ArrayList<>();
		Student s1 = new Student();
		s1.setId(1);
		s1.setName("Chandan");
		
		Student s2 = new Student();
		s2.setId(2);
		s2.setName("Amit");
		
		list.add(s1);
		list.add(s2);
		
		map.put("101", list);
		
		if(collegeId.equals("102"))
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		
		/*if(collegeId.equals("103"))
			throw new BusinessException("its Business exception");*/
		return new ResponseEntity<Map<String, List<Student>>>(map,HttpStatus.OK);
		
	}
	
}
