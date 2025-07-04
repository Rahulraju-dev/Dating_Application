package com.dating.Dating_Recomendation_Engine.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dating.Dating_Recomendation_Engine.entity.User;
import com.dating.Dating_Recomendation_Engine.repository.UserRepository;
import com.dating.Dating_Recomendation_Engine.util.UserGender;


@Repository
public class UserDao {
	@Autowired
	UserRepository userRepository;

	public User saveUser(User user) {
		return	userRepository.save(user);	
	}

	public List<User> findAllMaleUsers() {
		return userRepository.findByGender(UserGender.MALE);
	}

	public List<User> findAllFemaleUsers() {
		return	userRepository.findByGender(UserGender.FEMALE);
	}

	public Optional<User> findUserById(int id) {
		return userRepository.findById(id);
	}

	public List<User> findUserByAge(int age) {
		return userRepository.findUserByAge(age);
	}

	public Optional<User> findUserByName(String name) {
		return userRepository.findUserByName(name);
	}

	public List<User> findUserByPhone(long phone) {
		return userRepository.findUserByPhone(phone);
	}

	public Optional<User> findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	public List<User> searchByName(String letters) {
		return userRepository.searchByName(letters);
	}

	public List<User> searchByEmail(String letters) {
		return userRepository.searchByEmail(letters);
	}


	

}
