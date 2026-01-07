package com.project.backend.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	
	@GetMapping("/{id}")
	public String userDetail(@PathVariable("id") Integer id, Model model) {
		UserResponseDto user = userService.getUserDetail(id);
		model.addAttribute("user", user);
		return "user/detail";
	}
	
	@GetMapping("/create")
	public String userCreate(Model model) {
		model.addAttribute("userRequestDto", new UserRequestDto());
		return "user/create";
	}
	
	@PostMapping
	public String userCreate(@ModelAttribute UserRequestDto userRequestDto) {
		userService.createUser(userRequestDto);
		return "redirect:/users/create";
	}
}
