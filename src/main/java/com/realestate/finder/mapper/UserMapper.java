package com.realestate.finder.mapper;

import org.springframework.stereotype.Component;

import com.realestate.finder.dto.request.UserRequestDTO;
import com.realestate.finder.dto.response.UserResponseDTO;
import com.realestate.finder.entity.User;

@Component
public class UserMapper {

	public User toEntity(UserRequestDTO dto) {
		User user = new User();
		user.setFullName(dto.getFullName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		return user;
	}

	public UserResponseDTO toResponseDTO(User user) {
		UserResponseDTO dto = new UserResponseDTO();
		dto.setId(user.getId());
		dto.setFullName(user.getFullName());
		dto.setEmail(user.getEmail());
		return dto;
	}

}
