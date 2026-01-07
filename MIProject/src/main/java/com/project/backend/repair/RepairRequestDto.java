package com.project.backend.repair;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepairRequestDto {
	
	private String memo;
	private RepairStatus status;
}
