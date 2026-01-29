package com.project.backend.result;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.inspection.Inspection;
import com.project.backend.inspection.InspectionRepository;
import com.project.backend.inspection.InspectionStatus;
import com.project.backend.standard.Standard;
import com.project.backend.standard.StandardRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ResultService {
	
	private final ResultRepository resultRepository;
	private final InspectionRepository inspectionRepository;
	private final StandardRepository standardRepository;
	
	//점검결과
	public List<ResultResponseDto> getList() {
		return resultRepository.findAll().stream().map(ResultResponseDto::from).toList();
	}
	
	//점검에 따른 결과 리스트
	public List<ResultResponseDto> getListByInspection(Integer inspectionId) {
		return resultRepository.findByInspectionId(inspectionId).stream().map(ResultResponseDto::from).toList();
	}
	
	public ResultResponseDto getDetail(Integer id) {
		Result result = resultRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
		return ResultResponseDto.from(result);
	}
	
	public ResultResponseDto createResult(ResultRequestDto dto) {
		
		Inspection inspection = inspectionRepository.findById(dto.getInspectionId()).orElseThrow(() -> new IllegalArgumentException("점검내용을 찾을 수 없습니다."));
		Standard standard = standardRepository.findById(dto.getStandardId()).orElseThrow(() -> new IllegalArgumentException("점검기준을 찾을 수 없습니다."));
		
		Result result = new Result();
		result.setInspection(inspection);
		result.setStandard(standard);
		result.setStatus(dto.getStatus());
		result.setMemo(dto.getMemo());
		
		resultRepository.save(result);
		return ResultResponseDto.from(result);
	}
	
	//inspection 상태가 IN_PROGRESS (점검 중) 일 때만 수정 가능
	public ResultResponseDto modifyResult(Integer id, ResultRequestDto dto) {
		Result result = resultRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("점검 결과를 찾을 수 없습니다."));
		Inspection inspection = result.getInspection();
		
		if(inspection.getStatus() != InspectionStatus.IN_PROGRESS) {
			throw new IllegalStateException("점검 중인 상태만 수정이 가능합니다.");
		}
		
		result.setStatus(dto.getStatus());
		result.setMemo(dto.getMemo());
		return ResultResponseDto.from(result);
	}
}
