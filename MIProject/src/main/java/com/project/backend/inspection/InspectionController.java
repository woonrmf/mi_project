package com.project.backend.inspection;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.backend.result.ResultResponseDto;
import com.project.backend.result.ResultService;
import com.project.backend.standard.StandardResponseDto;
import com.project.backend.standard.StandardService;
import com.project.backend.user.User;
import com.project.backend.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inspections")
public class InspectionController {
	
	private final InspectionService inspectionService;
	private final UserRepository userRepository;
	private final ResultService resultService;
	private final StandardService standardService;
	
	@GetMapping("/list")
	public String getList(Model model) {
		List<InspectionResponseDto> list = inspectionService.getList();
		model.addAttribute("list", list);
		return "inspection/list";
	}
	
	@GetMapping("/{id}")
	public String getDetail(@PathVariable("id") Integer id, Model model) {
		InspectionResponseDto inspection = inspectionService.getDetail(id);
		List<ResultResponseDto> resultList = resultService.getListByInspection(id);
		List<StandardResponseDto> standardList = standardService.getList();
		
		model.addAttribute("inspection", inspection);
		model.addAttribute("resultList", resultList);
		model.addAttribute("standardList", standardList);
		model.addAttribute("inspectionRequestDto", new InspectionRequestDto());
		return "inspection/detail";
	}
	
	@PostMapping("/create/{machineId}")
	public String createInspection(@PathVariable("machineId") Integer machineId) {
		inspectionService.createInspection(machineId);
		return "redirect:/inspections/list";
	}
	
	@PostMapping("/start/{id}")
	public String startInspection(@PathVariable("id") Integer id, Principal principal) {
		User user = userRepository.findByLoginId(principal.getName()).orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
		
		inspectionService.startInspection(id, user);
		return "redirect:/inspections/" + id;
	}
	
	@PostMapping("/end/{id}")
	public String endInspection(@PathVariable("id") Integer id, InspectionRequestDto dto) {
		inspectionService.endInspection(id, dto);
		return "redirect:/inspections/" +id;
	}
}
