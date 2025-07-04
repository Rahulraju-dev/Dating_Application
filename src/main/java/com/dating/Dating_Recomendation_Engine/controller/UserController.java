package com.dating.Dating_Recomendation_Engine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dating.Dating_Recomendation_Engine.entity.User;
import com.dating.Dating_Recomendation_Engine.service.UserService;

@RestController //used to access the client request
//@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<?> saveUser(@RequestBody User user){
		return userService.saveUser(user);
	}
	@GetMapping("/users/gender/male")
	public ResponseEntity<?> findAllMaleUsers(){
		return userService.findAllMaleUsers();
	}
	@GetMapping("/users/gender/female")
	public ResponseEntity<?> findAllFemaleUsers(){
		return userService.findAllFemaleUsers();
	}
	//find by age id name email pohone is - task completed
	@GetMapping("/users/id/{id}")
	public ResponseEntity<?> findUserById(@PathVariable int id){
		return userService.findUserById(id);
	}
	@GetMapping("/users/age/{age}")
	public ResponseEntity<?> findUserByAge(@PathVariable int age){
		return userService.findUserByAge(age);
	}
	
	@GetMapping("/users/name/{name}")
	public ResponseEntity<?> findUserByName(@PathVariable String name){
		return userService.findUserByName(name);
	}
	
	@GetMapping("/users/phone/{phone}")
	public ResponseEntity<?> findUserByPhone(@PathVariable long phone){
		return userService.findUserByPhone(phone);
	}
	
	@GetMapping("/users/email/{email}")
	public ResponseEntity<?> findUserByEmail(@PathVariable String email){
		return userService.findUserByEmail(email);
	}
	
	@GetMapping("/users/best-match/{id}/{top}")
//	which user id and how many top matches to find
	public ResponseEntity<?> findBestMatch(@PathVariable int id,@PathVariable int top){
		return userService.findBestMatch(id,top);
	}
	
	
	//matching names should come can reduce the
	@GetMapping("/users/search/name/{letters}")
	public ResponseEntity<?> searchByName(@PathVariable String letters){
		return userService.searchByName(letters);
	}
	@GetMapping("/users/search/email/{letters}")
	public ResponseEntity<?> searchByEmail(@PathVariable String letters){
		return userService.searchByEmail(letters);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
