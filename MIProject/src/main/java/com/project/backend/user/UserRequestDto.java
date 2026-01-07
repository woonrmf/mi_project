package com.project.backend.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
	private Integer id;
	private String loginId;
	private String password;
	private String name;
	private Role role;
}
