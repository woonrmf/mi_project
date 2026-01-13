package com.project.backend.standard;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StandardService {

	private final StandardRepository standardRepository;
	
	public List<StandardResponseDto> getList() {
		return standardRepository.findAll().stream().map(StandardResponseDto::from).toList();
	}
	
	public StandardResponseDto getDetail(Integer id) {
		Standard standard = standardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 기준을 찾을 수 없습니다."));
		return StandardResponseDto.from(standard);
	}
	
	public StandardResponseDto createStandard(StandardRequestDto dto) {
		Standard standard = new Standard();
		standard.setName(dto.getName());
		standard.setMemo(dto.getMemo());
		standardRepository.save(standard);
		return StandardResponseDto.from(standard);
	}
	
	public void modifyStandard(Integer id, StandardRequestDto dto) {
		Standard standard = standardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 기준을 찾을 수 없습니다."));
		standard.setName(dto.getName());
		standard.setMemo(dto.getMemo());
	}
	
	public void deleteStandard(Integer id) {
		Standard standard = standardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 기준을 찾을 수 없습니다."));
		standardRepository.delete(standard);
	}
	
}
