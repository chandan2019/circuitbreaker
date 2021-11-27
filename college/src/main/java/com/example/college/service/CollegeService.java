package com.example.college.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CollegeService {

	ResponseEntity<Map> getStudentByCollege(String collegeId);
}
