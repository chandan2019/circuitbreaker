package com.example.college.service;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class CollegeServiceImpl implements CollegeService {

	public static final String COLLEGE_SERVICE = "collegeService";

	RestTemplate restTemplate = new RestTemplate();
	
	int count = 0;

	@Override
	@Retry(name = COLLEGE_SERVICE/*, fallbackMethod = "fallbackForRetry"*/)
	@CircuitBreaker(name = COLLEGE_SERVICE, fallbackMethod = "getAllStudentFallback")
	public ResponseEntity<Map> getStudentByCollege(String collegeId) {

		ResponseEntity<Map> forEntity = null;
		String url = "http://localhost:8080/student/{collegeId}";
		System.out.println(" count = " + ++count);
		
		HttpHeaders headers = new HttpHeaders();

		HttpEntity request = new HttpEntity(headers);
		forEntity =  restTemplate.exchange(url, HttpMethod.GET,request, Map.class,collegeId);
		 
		
		//return testCircuitBreakerInFunctionalProgramming().apply(collegeId);
		//throw new RuntimeException("abc");
		return forEntity;
	}
	
	/*@CircuitBreaker(name = COLLEGE_SERVICE, fallbackMethod = "getAllStudentFallback")
	public Function<String, ResponseEntity<Map>> testCircuitBreakerInFunctionalProgramming(){
		//return getStudentByCollegeId;
		
		return (collegeId)->{
			
			ResponseEntity<Map> forEntity = null;
			String url = "http://localhost:8080/student/{collegeId}";
			
			HttpHeaders headers = new HttpHeaders();

			HttpEntity request = new HttpEntity(headers);
			forEntity =  restTemplate.exchange(url, HttpMethod.GET,request, Map.class,collegeId);
			
			return forEntity;

			
		};
		
	};
	
	private Function<String, ResponseEntity<Map>> getStudentByCollegeId = (collegeId)->{
		
		
		ResponseEntity<Map> forEntity = null;
		String url = "http://localhost:8080/student/{collegeId}";
		
		HttpHeaders headers = new HttpHeaders();

		HttpEntity request = new HttpEntity(headers);
		forEntity =  restTemplate.exchange(url, HttpMethod.GET,request, Map.class,collegeId);
		
		return forEntity;
		
	};
*/
	
	
/*	public ResponseEntity<Map> fallbackForRetry(String collegeId, Throwable t) {
		System.out.println("retry fallback");
		
		return null;
		
	}*/
	
	public ResponseEntity<Map> getAllStudentFallback(String collegeId, Throwable t) {

		System.out.println("fallback method");
		return null;
	}
	
	public ResponseEntity<Map> getAllStudentFallback(String collegeId, CallNotPermittedException t) {

		System.out.println("fallback call not permitted method");
		return null;
	}
	
/*	public Function<String, ResponseEntity<Map>> getAllStudentFallback(String collegeId, Throwable t) {

		System.out.println("fallback method");
		return null;
	}*/
	
	
/*	public Function<String, ResponseEntity<Map>> getAllStudentFallback(String collegeId, ConnectException t) {

		System.out.println("connect fallback method");
		return null;
	}*/
	/*public ResponseEntity<Map> getAllStudentFallback(String collegeId, ConnectException t) {

		System.out.println("Connect fallback method");
		return null;
	}*/
	
	
	
	/*public ResponseEntity<Map> getAllStudentFallback(String collegeId, CallNotPermittedException t) {

		System.out.println("fallback method");
		return null;
	}*/

}
