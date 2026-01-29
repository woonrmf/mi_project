package com.project.backend.machine;

import java.time.LocalDateTime;
import java.util.Comparator;

import com.project.backend.inspection.Inspection;
import com.project.backend.inspection.InspectionStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MachineResponseDto {
	
	private Integer id;
	private String name;
	private String mCode;
	private String location;
	private MachineStatus status;
	private LocalDateTime installDate;
	private InspectionStatus inspectionStatus;
	
	public static MachineResponseDto from(Machine machine) {
		InspectionStatus inspectionStatus = machine.getInspectionList().stream()
	            .max(Comparator.comparing(Inspection::getId)) // 최신 점검
	            .map(Inspection::getStatus)
	            .orElse(null);
		
		return new MachineResponseDto(
				machine.getId(),
				machine.getName(),
				machine.getMCode(),
				machine.getLocation(),
				machine.getStatus(),
				machine.getInstallDate(),
				inspectionStatus);
	}
}
