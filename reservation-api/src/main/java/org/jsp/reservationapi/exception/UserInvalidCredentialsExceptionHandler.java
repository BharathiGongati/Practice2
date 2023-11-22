package org.jsp.reservationapi.exception;

public class UserInvalidCredentialsExceptionHandler extends RuntimeException {
	@Override
    public String getMessage() {
    	return "Invalid  email or password";
    }


}
