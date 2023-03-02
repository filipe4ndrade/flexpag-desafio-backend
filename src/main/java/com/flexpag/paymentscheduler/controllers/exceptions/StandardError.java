package com.flexpag.paymentscheduler.controllers.exceptions;

import java.io.Serializable;

import lombok.Data;

@Data
public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	public StandardError() {
		
	}

	public StandardError(Long timestamp, Integer status, String error, String message, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}
	
	
}
