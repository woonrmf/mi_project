package com.project.backend.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResultResponseDto {
	
	private Integer id;
	private ResultStatus status;
	private String memo;
	private Integer standardId;
	private String standardName;
	private Integer inspectionId;
	
	public static ResultResponseDto from(Result result) {
		return new ResultResponseDto(
				result.getId(), 
				result.getStatus(), 
				result.getMemo(),
				result.getStandard().getId(),
				result.getStandard().getName(),
				result.getInspection().getId());
	}
}
