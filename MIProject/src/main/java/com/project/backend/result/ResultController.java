package com.project.backend.result;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/results")
public class ResultController {
	
	private final ResultService resultService;
	
	//전체 리스트
	@GetMapping("/list")
	public String getList(Model model) {
		List<ResultResponseDto> list = resultService.getList();
		return "result/list";
	}
	
	//특정 점검에 대한 리스트
	@GetMapping("/inspection/{inspectionId}")
	public String getListByInspection(@PathVariable("inspectionId") Integer inspectionId, Model model) {
		List<ResultResponseDto> list = resultService.getListByInspection(inspectionId);
		model.addAttribute("list", list);
		model.addAttribute("inspectionId", inspectionId);
		return "result/list";
	}
	
	//상세 조회
	@GetMapping("/{id}")
	public String getDetail(@PathVariable("id") Integer id, Model model) {
		ResultResponseDto result = resultService.getDetail(id);
		model.addAttribute("result", result);
		return "result/detail";
	}
	
	//점검 결과 get
	@GetMapping("/create")
	public String createResult(@RequestParam Integer inspectionId, @RequestParam Integer standardId, Model model) {
		ResultRequestDto dto = new ResultRequestDto();
		dto.setInspectionId(inspectionId);
		dto.setStandardId(standardId);
		
		model.addAttribute("resultRequestDto", dto);
		model.addAttribute("status", ResultStatus.values());
		return "result/create";
	}
	
	//결과 작성 post
	@PostMapping("/create")
	public String createResult(@ModelAttribute ResultRequestDto dto) {
		resultService.createResult(dto);
		return "redirect:/results/inspection/" + dto.getInspectionId();
	}
	
	//수정 다른 사람(관리자, 수리자)도 확인하게 상세조회랑 수정 분리
	@GetMapping("/modify/{id}")
	public String modifyResult(@PathVariable("id") Integer id, Model model) {
		ResultResponseDto result = resultService.getDetail(id);
		
		ResultRequestDto dto = new ResultRequestDto();
		dto.setInspectionId(result.getInspectionId()); //수정용 x 리다이렉트용 o
		dto.setStatus(result.getStatus());
		dto.setMemo(result.getMemo());
		
		model.addAttribute("id", id);
		model.addAttribute("resultRequestDto", dto);
		model.addAttribute("status", ResultStatus.values());
		return "result/modify";
	}
	
	@PostMapping("/modify/{id}")
	public String modifyResult(@PathVariable("id") Integer id, @ModelAttribute ResultRequestDto dto) {
		resultService.modifyResult(id, dto);
		return "redirect:/results/inspection/" + dto.getInspectionId();
	}
}
