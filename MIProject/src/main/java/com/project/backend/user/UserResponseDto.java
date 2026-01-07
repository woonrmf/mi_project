package com.project.backend.user;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
	
	private Integer id;
	private String loginId;
	private String name;
	private Role role;
	private LocalDateTime creDate;
	
	public static UserResponseDto from(User user) {
		return new UserResponseDto (
				user.getId(),
				user.getLoginId(),
				user.getName(),
				user.getRole(),
				user.getCreDate());
	}
}
