package com.project.backend.repair;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RepairResponseDto {
	
	private Integer id;
	private String memo;
	private RepairStatus status;
	private LocalDateTime repairDate;
	private String userName;
	private String role;
	private Integer resultId;
	
	public static RepairResponseDto from(Repair repair) {
		return new RepairResponseDto(
				repair.getId(), 
				repair.getMemo(), 
				repair.getStatus(), 
				repair.getRepairDate(), 
				repair.getUser().getName(), 
				repair.getUser().getRole().name(), 
				repair.getResult().getId());
	}
	
}
