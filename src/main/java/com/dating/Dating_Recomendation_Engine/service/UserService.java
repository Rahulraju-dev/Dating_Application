package com.dating.Dating_Recomendation_Engine.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dating.Dating_Recomendation_Engine.dao.UserDao;
import com.dating.Dating_Recomendation_Engine.dto.MatchingUser;
import com.dating.Dating_Recomendation_Engine.entity.User;
import com.dating.Dating_Recomendation_Engine.util.UserGender;
import com.dating.Dating_Recomendation_Engine.util.UserSorting;

@Service//validate service
public class UserService {
	@Autowired
	UserDao userDao;

	public ResponseEntity<?> saveUser(User user) {
		User savedUser = userDao.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	public ResponseEntity<?> findAllMaleUsers() {
		List<User> maleUsers= userDao.findAllMaleUsers();
		if(maleUsers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no male users present in the base table");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(maleUsers);
		}
	}
	public ResponseEntity<?> findAllFemaleUsers() {
		List<User> femaleUsers=userDao.findAllFemaleUsers();
		if(femaleUsers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no femake users found");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(femaleUsers);
		}
	}
	public ResponseEntity<?> findBestMatch(int id, int top) {
	//first i need to find user
	Optional<User> optional = userDao.findUserById(id);
//	 from dao
//	then i am checking is empty or not
	if(optional.isEmpty()) 
	{
	 return	ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid user id,unable to find best macthesuser found ");
	}
//	if id present then get that user
	User user=optional.get();
//how to get opposite gender users then to store in list
	List<User> users=null;//	
	
	if(user.getGender().equals(UserGender.MALE)) 
	{
		users=userDao.findAllFemaleUsers();
	}
	else 
	{
		users=userDao.findAllMaleUsers();
	}
//	for(User u:users)
//		System.out.println(u);

	// am creating matching users this contains 2 extra fields - agediff and matching interest
	List<MatchingUser> matchingUsers=new ArrayList<>();
	
//	here i am iterating the list of opposite gender users
	for(User u:users)
	{
		//creating matching user object and for this matching user id get details and put in matching user
		MatchingUser mu = new MatchingUser();
		mu.setId(u.getId());
		mu.setName(u.getName());
		mu.setEmail(u.getEmail());
		mu.setPhone(u.getPhone());
		mu.setAge(u.getAge());
		mu.setInterests(u.getInterests());
		mu.setGender(u.getGender());
//		checking Doni age - female user some times we get negative values soo i am useing absolute Math(abs)
		mu.setAgeDiff(Math.abs(user.getAge()-u.getAge()));		

//..........................dhoni interest taking here
		List<String> interest1=user.getInterests();
//		current iteration user interests
		List<String> interest2=u.getInterests();		
//		matching interest count is zero 
		int mic=0;
//		in collection methods isempty, addall, add,   contains
		for(String s:interest1) {
			if(interest2.contains(s)) {
				mic++;
			}
		}
		mu.setMic(mic);
//		adding matching user in that Matching user list
		matchingUsers.add(mu);
	}
//	now sorting will com into the picture 
	
//if it is comaprable type means we can solve directly but we have comparator so we need to create a user sorting class
	
		Comparator<MatchingUser> c=new UserSorting();
		Collections.sort(matchingUsers,c);
		List<MatchingUser> result=new ArrayList<>();
		for(MatchingUser m: matchingUsers) {
			if(top==0) {
				break;
			}else {
				result.add(m);
				top--;
			}
		}
	
	return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	public ResponseEntity<?> findUserById(int id) {		Optional<User> userById = userDao.findUserById(id);
		if(userById.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(userById);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid id user not found");
		}
	}

	public ResponseEntity<?> findUserByAge(int age) {
		List<User> userByAge=userDao.findUserByAge(age);
		if(userByAge.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid age ,it does not match with database users ");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(userByAge);

		}
		
		
	}

	public ResponseEntity<?> findUserByName(String name) {
	Optional<User> userByName=	userDao.findUserByName(name);
	if(userByName.isEmpty()) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("sorry given name does not match with data base");
	}else {
		return ResponseEntity.status(HttpStatus.OK).body(userByName);
	}
	}

	public ResponseEntity<?> findUserByPhone(long phone) {
		List<User> userByphone=userDao.findUserByPhone(phone);
		if(userByphone.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("phone number doesnot match so no users found");
		}else{
			return ResponseEntity.status(HttpStatus.OK).body(userByphone);
		}
	}

	public ResponseEntity<?> findUserByEmail(String email) {
		Optional<User> userByEmail=userDao.findUserByEmail(email);
		if(userByEmail.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email does not match with data in database");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(userByEmail.get());
		}

	}
	public ResponseEntity<?> searchByName(String letters){
		
		List<User> users=userDao.searchByName("%"+letters+"%");
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("users not matching with letters "+letters);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}
	}
	public ResponseEntity<?> searchByEmail(String letters) {
		List<User> users=userDao.searchByEmail("%"+letters+"%");
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("users not matching with letters in emails"+letters);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}
	}
}













