package com.project.backend.inspection;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.machine.Machine;
import com.project.backend.machine.MachineRepository;
import com.project.backend.machine.MachineStatus;
import com.project.backend.result.Result;
import com.project.backend.result.ResultStatus;
import com.project.backend.user.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class InspectionService {

	private final InspectionRepository inspectionRepository;
	private final MachineRepository machineRepository;
	
	public List<InspectionResponseDto> getList() {
		return inspectionRepository.findAll().stream().map(InspectionResponseDto::from).toList();
	}
	
	public InspectionResponseDto getDetail(Integer id) {
		Inspection inspection = inspectionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("점검 내용을 찾을 수 없습니다."));
		return InspectionResponseDto.from(inspection);
	}
	
	//점검 생성
	public InspectionResponseDto createInspection(Integer machineId) {
		Machine machine = machineRepository.findById(machineId).orElseThrow(() -> new IllegalArgumentException("해당 기계를 찾을 수 없습니다."));
		
		if(machine.getStatus() != MachineStatus.NEED_INSPECTION) {
			throw new IllegalStateException("점검 대상이 아닙니다.");
		}
		
		Inspection inspection = new Inspection();
		inspection.setMachine(machine);
		inspection.setStatus(InspectionStatus.READY);
		
		inspectionRepository.save(inspection);
		return InspectionResponseDto.from(inspection);
	}
	
	//점검 시작
	public void startInspection(Integer id, User user) {
		Inspection inspection = inspectionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("점검 내용을 찾을 수 없습니다."));
		
		if(inspection.getStatus() != InspectionStatus.READY) {
			throw new IllegalStateException("점검 전 상태가 아닙니다.");
		}
		
		Machine machine = inspection.getMachine();
		
		inspection.setStatus(InspectionStatus.IN_PROGRESS);
		inspection.setUser(user);
		machine.setStatus(MachineStatus.INSPECTION);
	}
	
	//점검 완료
	public void endInspection(Integer id, InspectionRequestDto dto) {
		Inspection inspection = inspectionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("점검 내용을 찾을 수 없습니다."));
		
		if(inspection.getStatus() != InspectionStatus.IN_PROGRESS) {
			throw new IllegalStateException("점검 중인 상태가 아닙니다.");
		}
		
		inspection.setMemo(dto.getMemo());
		inspection.setStatus(InspectionStatus.COMPLETED);
		
		machineStatusByResult(inspection);
	}
	
	//점검 결과에 따른 기계 상태
	private void machineStatusByResult(Inspection inspection) {
		Machine machine = inspection.getMachine();
		List<Result> resultList = inspection.getResultList();
		
		if(resultList == null || resultList.isEmpty()) {
			throw new IllegalStateException("점검 결과가 없습니다.");
		}
		
		boolean fail = resultList.stream().anyMatch(r -> r.getStatus() == ResultStatus.FAIL);
		boolean needCheck = resultList.stream().anyMatch(r -> r.getStatus() == ResultStatus.NEED_CHECK);
		
		if(fail) {
			machine.setStatus(MachineStatus.ERROR);
		} else if (needCheck) {
			machine.setStatus(MachineStatus.NEED_INSPECTION);
		} else {
			machine.setStatus(MachineStatus.NORMAL);
		}
	}
}
