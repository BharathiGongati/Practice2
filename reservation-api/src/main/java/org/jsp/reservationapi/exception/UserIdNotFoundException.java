package org.jsp.reservationapi.exception;

public class UserIdNotFoundException  extends RuntimeException{
	@Override
    public String getMessage() {
    	return "Invalid id";
    }


}
