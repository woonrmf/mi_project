package com.project.backend.machine;

import java.time.LocalDateTime;

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
	
	public static MachineResponseDto from(Machine machine) {
		return new MachineResponseDto(
				machine.getId(),
				machine.getName(),
				machine.getMCode(),
				machine.getLocation(),
				machine.getStatus(),
				machine.getInstallDate());
	}
}
