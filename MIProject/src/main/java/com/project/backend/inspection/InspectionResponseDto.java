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
	private String machineLocation;
	
	public static InspectionResponseDto from(Inspection inspection) {
		String userName = null;
	    String role = null;

	    if (inspection.getUser() != null) {
	        userName = inspection.getUser().getName();
	        role = inspection.getUser().getRole().name();
	    }
	    
		return new InspectionResponseDto(
				inspection.getId(),
				inspection.getMemo(), 
				inspection.getStatus(), 
				inspection.getInspectionDate(), 
				userName,
				role,
				inspection.getMachine().getName(),
				inspection.getMachine().getMCode(),
				inspection.getMachine().getLocation());
	}
}
