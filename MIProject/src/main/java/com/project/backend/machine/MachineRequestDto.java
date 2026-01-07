package com.project.backend.machine;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MachineRequestDto {
	
	private String name;
	private String mCode;
	private String location;
	private MachineStatus status;
}
