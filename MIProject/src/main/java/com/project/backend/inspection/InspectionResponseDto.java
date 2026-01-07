package com.project.backend.inspection;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InspectionResponseDto {
	
	private Integer id;
	private String memo;
	private InspectionStatus status;
	private LocalDateTime inspectionDate;
	private String userName;
	private String role;
	private String machineName;
	private String mCode;
	
	public static InspectionResponseDto from(Inspection inspection) {
		return new InspectionResponseDto(
				inspection.getId(),
				inspection.getMemo(), 
				inspection.getStatus(), 
				inspection.getInspectionDate(), 
				inspection.getUser().getName(),
				inspection.getUser().getRole().name(),
				inspection.getMachine().getName(),
				inspection.getMachine().getMCode());
	}
}
