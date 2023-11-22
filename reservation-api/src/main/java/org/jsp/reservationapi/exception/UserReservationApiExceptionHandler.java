package org.jsp.reservationapi.exception;

import org.jsp.reservationapi.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class UserReservationApiExceptionHandler {
	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleINFE(UserIdNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessege(exception.getMessage());
		structure.setData("User not found");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(UserInvalidCredentialsExceptionHandler.class)
	public ResponseEntity<ResponseStructure<String>> handleINFE(UserInvalidCredentialsExceptionHandler exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessege(exception.getMessage());
		structure.setData("User not found");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		}

}
