package com.project.backend.inspection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InspectionRequestDto {
	
	private String memo;
	private InspectionStatus status;
}
