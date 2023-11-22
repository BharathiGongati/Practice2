package org.jsp.reservationapi.exception;

public class IdNotFoundException extends RuntimeException {
	@Override
    public String getMessage() {
    	return "Invalid id";
    }

}
