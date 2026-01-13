package com.project.backend.standard;

import java.util.List;

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
@RequestMapping("/standards")
public class StandardController {
	
	private final StandardService standardService;
	
	@GetMapping("/list")
	public String getList(Model model) {
		List<StandardResponseDto> getList = standardService.getList();
		model.addAttribute("getList", getList);
		return "standard/list";
	}
	
	@GetMapping("/{id}")
	public String getDetail(@PathVariable("id") Integer id, Model model) {
		StandardResponseDto standard = standardService.getDetail(id);
		model.addAttribute("standard", standard);
		return "standard/detail";
	}

	@PostMapping("/{id}")
	public String modify(@PathVariable("id") Integer id, @ModelAttribute StandardRequestDto dto) {
		standardService.modifyStandard(id, dto);
		return "redirect:/standards/" + id;
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("standardRequestDto", new StandardRequestDto());
		return "standard/create";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute StandardRequestDto dto) {
		standardService.createStandard(dto);
		return "redirect:/standards/list";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		standardService.deleteStandard(id);
		return "redirect:/standards/list";
	}
}
