package com.project.backend.user;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
	
	private final UserRepository userRepository;
	
	public UserResponseDto getUserDetail(Integer id) {
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자"));
		return UserResponseDto.from(user);
	}
	
	public UserResponseDto createUser(UserRequestDto userRequestDto) {
		
		if(userRepository.findByLoginId(userRequestDto.getLoginId()).isPresent()) {
			throw new IllegalStateException("이미 존재하는 ID");
		}
		
		User user = new User();
		user.setLoginId(userRequestDto.getLoginId());
		user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
		user.setName(userRequestDto.getName());
		user.setRole(userRequestDto.getRole());
		
		userRepository.save(user);
		return UserResponseDto.from(user);
	}
}
