package org.jsp.reservationapi.exception;

public class InvalidCredentialsException extends RuntimeException {
	@Override
    public String getMessage() {
    	return "Invalid phone or email or password";
    }

}
