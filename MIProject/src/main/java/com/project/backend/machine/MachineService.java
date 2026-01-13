package com.project.backend.machine;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MachineService {
	
	private final MachineRepository machineRepository;
	
	public List<MachineResponseDto> machineList() {
		return machineRepository.findAll().stream().map(MachineResponseDto::from).toList();
	}
	
	public MachineResponseDto getMachineDetail(Integer id) {
		Machine machine = machineRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 기계를 찾을 수 없습니다."));
		return MachineResponseDto.from(machine);
	}
	
	public MachineResponseDto createMachine(MachineRequestDto dto) {
		Machine machine = new Machine();
		machine.setName(dto.getName());
		machine.setMCode(dto.getMCode());
		machine.setLocation(dto.getLocation());
		machine.setStatus(MachineStatus.NORMAL); //기계 생성 시 기계 상태 nomal 기본값
		machineRepository.save(machine);
		return MachineResponseDto.from(machine);
	}
	
	public void modifyMachine(Integer id, MachineRequestDto dto) {
		Machine machine = machineRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 기계를 찾을 수 없습니다."));
		machine.setName(dto.getName());
		machine.setLocation(dto.getLocation());
	}
	
	public void requestMachine(Integer id) {
		Machine machine = machineRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 기계를 찾을 수 없습니다."));
		if(machine.getStatus() != MachineStatus.NORMAL) {
			throw new IllegalArgumentException("점검 요청 대상이 아닙니다."); //기계 상태가 nomal이 아니면 점검 요청 대상 아님
		}
		machine.setStatus(MachineStatus.NEED_INSPECTION); //점검 필요로 상태 변경
	}
}
