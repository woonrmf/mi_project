package com.project.backend.machine;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/machines")
public class MachineController {
	
//관리자가 기계를 생성, 상태를 nomal -> need_inspection으로 변경하는 역할을 함
	
	private final MachineService machineService;
	
	@GetMapping("/list")
	public String getList(Model model) {
		List<MachineResponseDto> mList = machineService.machineList();
		
		Map<String, List<MachineResponseDto>> mMap = mList.stream().collect(Collectors.groupingBy(MachineResponseDto::getLocation));
		
		model.addAttribute("mMap", mMap);
		return "machine/list";
	}
	
	@GetMapping("/{id}")
	public String getMachine(@PathVariable("id") Integer id, Model model) {
		MachineResponseDto machine = machineService.getMachineDetail(id);
		model.addAttribute("machine", machine);
		return "machine/detail";
	}
	
	@GetMapping("/admin/create")
	@PreAuthorize("hasRole('ADMIN')")
	public String createMachine(Model model) {
		model.addAttribute("machineRequestDto", new MachineRequestDto());
		return "machine/create";
	}
	
	@PostMapping("/admin/create")
	@PreAuthorize("hasRole('ADMIN')")
	public String createMachine(@ModelAttribute MachineRequestDto dto) {
		machineService.createMachine(dto);
		return "redirect:/";
	}
	
	@PostMapping("/admin/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String modifyMachine(@PathVariable("id") Integer id, @ModelAttribute MachineRequestDto dto) {
		machineService.modifyMachine(id, dto);
		return "redirect:/machines/" + id;
	}
	
	@PostMapping("/admin/{id}/request")
	@PreAuthorize("hasRole('ADMIN')")
	public String requestMachine(@PathVariable("id") Integer id) {
		machineService.requestMachine(id);
		return "redirect:/machines/" + id;
	}
}
