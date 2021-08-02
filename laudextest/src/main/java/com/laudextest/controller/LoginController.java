package com.laudextest.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.laudextest.model.User;
import com.laudextest.model.Role;
import com.laudextest.repository.UserRepository;
import com.laudextest.utils.MessageConnectionStatus;
import com.laudextest.utils.ResponseWrapper;

@RestController
@RequestMapping("api/login")
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<ResponseWrapper> getLogin(@RequestBody User login){
		ResponseWrapper response = new ResponseWrapper();
		User user;
		Boolean access = false;
		
		try {
			user = userRepository.findByEmail( login.getEmail() ).get(0);
			if ( user != null ) {
				if ( user.getRoles() != null  ) {
					for (Iterator<Role> roleIt = user.getRoles().iterator(); roleIt.hasNext();) {
						Role role = (Role) roleIt.next();
						if (role.getName().equals("admin")) {
							access = true;
						}
					}
				}
					
				if (access) {
					if ( login.getPassword().equals( user.getPassword() ) ) {
						response.setStatus( MessageConnectionStatus.SUCCESS );
						response.setData(user);
						response.setMessageStatus("Access Granted");
					} else {
						response.setStatus( MessageConnectionStatus.ERROR );
						response.setMessageStatus("User or Password Incorrect");
					}
				} else {
					response.setStatus( MessageConnectionStatus.ERROR );
					response.setMessageStatus("Access denied");
				}
			} else {
				response.setStatus( MessageConnectionStatus.ERROR );
				response.setMessageStatus("Wrong Credentials");
			}
		} catch (Exception e) {
			response.setStatus( MessageConnectionStatus.ERROR );
			response.setMessageStatus("Bad Request");
		}
		
		return new ResponseEntity<ResponseWrapper>(response,HttpStatus.OK);
	}
}
