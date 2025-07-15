package com.realestate.finder.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.realestate.finder.dto.request.UserRequestDTO;
import com.realestate.finder.dto.response.UserResponseDTO;
import com.realestate.finder.exception.UserNotFoundException;
import com.realestate.finder.entity.CustomUserDetails;
import com.realestate.finder.entity.User;
import com.realestate.finder.mapper.UserMapper;
import com.realestate.finder.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public UserResponseDTO createUser(UserRequestDTO requestDTO) {
		User user = userMapper.toEntity(requestDTO);
		User saved = userRepository.save(user);
		return userMapper.toResponseDTO(saved);
	}

	@Override
	public List<UserResponseDTO> getAllUsers() {
		return userRepository.findAll().stream().map(userMapper::toResponseDTO).collect(Collectors.toList());
	}

	@Override
	public UserResponseDTO getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
		return userMapper.toResponseDTO(user);
	}

	@Override
	public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {
		User existing = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

		existing.setFullName(requestDTO.getFullName());
		existing.setEmail(requestDTO.getEmail());
		existing.setPassword(requestDTO.getPassword());

		User updated = userRepository.save(existing);
		return userMapper.toResponseDTO(updated);
	}

	@Override
	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
		userRepository.delete(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
		return new CustomUserDetails(user);
	}

	@Override
	public User findUserEntityByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
	}
}
