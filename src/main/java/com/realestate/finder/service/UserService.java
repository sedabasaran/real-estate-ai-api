package com.realestate.finder.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.realestate.finder.dto.request.UserRequestDTO;
import com.realestate.finder.dto.response.UserResponseDTO;
import com.realestate.finder.entity.User;

public interface UserService  extends UserDetailsService{

	UserResponseDTO createUser(UserRequestDTO requestDTO);

	List<UserResponseDTO> getAllUsers();

	UserResponseDTO getUserById(Long id);
	
	UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO);

	void deleteUser(Long id);
	
	User findUserEntityByEmail(String email);
	


}
