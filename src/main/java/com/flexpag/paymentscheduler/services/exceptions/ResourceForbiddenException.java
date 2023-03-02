package com.flexpag.paymentscheduler.services.exceptions;

public class ResourceForbiddenException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceForbiddenException(Object id) {
		super("There are no results for this operation.");
	}
	
}