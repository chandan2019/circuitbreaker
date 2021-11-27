package com.example.student.exception;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5498286135433975166L;

	public BusinessException(){
		super();
	}
	
	public BusinessException(String msg){
		super(msg);
	}
	
}
