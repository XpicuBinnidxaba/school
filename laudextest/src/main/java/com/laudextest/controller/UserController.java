package com.laudextest.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.laudextest.model.Role;
import com.laudextest.model.User;
import com.laudextest.repository.RoleRepository;
import com.laudextest.repository.UserRepository;
import com.laudextest.utils.ResponseWrapper;
import com.laudextest.utils.MessageConnectionStatus;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	ResponseEntity<ResponseWrapper> getAllUsers() {
		
		ResponseWrapper response = new ResponseWrapper();
		List<User> userList = new ArrayList<User>();
		
		try {
			userList = userRepository.findAll();
			response.setData(userList);
			response.setStatus( MessageConnectionStatus.SUCCESS );
			response.setMessageStatus("User listed");
		} catch (Exception e) {
		}
		return new ResponseEntity<ResponseWrapper>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<ResponseWrapper> newUser(@RequestBody User user) {
		ResponseWrapper response = new ResponseWrapper();
		List<User> userRegistered = new ArrayList<User>();
		List<Role> roles;
		
		try {
			userRegistered = userRepository.findByEmail( user.getEmail() );
			
			if ( userRegistered.size() <= 0 ) {
				
				if ( user.getRoles() != null) {
					roles = new ArrayList<Role> ();
					
					for (Iterator<Role> roleIt = user.getRoles().iterator(); roleIt.hasNext();) {
						Role role = (Role) roleIt.next();
						if( !roleRepository.findById( role.getId() ).isEmpty() ) {
							roles.add( roleRepository.findById( role.getId() ).get() );
						}
					}
					user.setRoles(roles);
				}
				
				userRepository.save(user);
				
				response.setStatus( MessageConnectionStatus.SUCCESS );
				response.setMessageStatus("User created");
			} else {
				response.setStatus( MessageConnectionStatus.ERROR );
				response.setMessageStatus("User already exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus( MessageConnectionStatus.ERROR );
			response.setMessageStatus("Bad request");
		}
		
		response.setData(user);
		
		return new ResponseEntity<ResponseWrapper>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	ResponseEntity<ResponseWrapper> updateUser(
			@PathVariable("id") Long id,
			@RequestBody User userMod) {
		ResponseWrapper response = new ResponseWrapper();
		Long userId;
		User user = new User();
		
		try {
			user = userRepository.findById(id).get();
			userId = user.getId();
			user = userMod;
			user.setId(userId);
			userRepository.save(user);
			response.setData(user);
			response.setStatus( MessageConnectionStatus.SUCCESS );
			response.setMessageStatus("User updated");
		} catch (Exception e) {
			response.setStatus( MessageConnectionStatus.ERROR );
			response.setMessageStatus("Error updated");
		}
		
		return new ResponseEntity<ResponseWrapper>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<ResponseWrapper> deleteUser(
			@PathVariable("id") Long id) {
		ResponseWrapper response = new ResponseWrapper();
		
		response.setData(id);
		try {
			userRepository.deleteById( id );
			response.setStatus( MessageConnectionStatus.SUCCESS );
			response.setMessageStatus("User deleted");
		} catch (Exception e) {
			response.setStatus( MessageConnectionStatus.ERROR );
			response.setMessageStatus("Error");
		}
		return new ResponseEntity<ResponseWrapper>(response, HttpStatus.OK);
	}
	
}
