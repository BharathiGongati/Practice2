package org.jsp.reservationapi.service;

import java.util.Optional;

import org.jsp.reservationapi.dao.UserDao;
import org.jsp.reservationapi.dto.User;
import org.jsp.reservationapi.dto.ResponseStructure;
import org.jsp.reservationapi.exception.IdNotFoundException;
import org.jsp.reservationapi.exception.InvalidCredentialsException;
import org.jsp.reservationapi.exception.UserIdNotFoundException;
import org.jsp.reservationapi.exception.UserInvalidCredentialsExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao dao;
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) 
	{
		ResponseStructure<User> structure=new ResponseStructure<>();
		structure.setData(dao.saveUser(user));
		structure.setMessege("User Saved With Id:"+user.getId());
		structure.setStatuscode(HttpStatus.CREATED.value());
	    return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		ResponseStructure<User> structure=new ResponseStructure<>();
		structure.setData(dao.updateUser(user));
		structure.setMessege("User Updated Successfully");
		structure.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.ACCEPTED) ;
	}
	
	public ResponseEntity<ResponseStructure<User>> findById(int id){
		ResponseStructure<User> structure=new ResponseStructure<>();
		Optional<User> recUser=dao.findById(id);
		if(recUser.isPresent()) {
			structure.setMessege("User found");
			structure.setData(recUser.get());
		
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK); 
		}
		throw new UserIdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyUser(String email,String password){
		ResponseStructure<User> structure=new ResponseStructure<>();
		Optional<User> recUser=dao.verifyUser(email,password);
		if(recUser.isPresent()) {
			structure.setMessege("User verified");
			structure.setData(recUser.get());
		
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK); 
		}
		throw new UserInvalidCredentialsExceptionHandler();
	}



}
