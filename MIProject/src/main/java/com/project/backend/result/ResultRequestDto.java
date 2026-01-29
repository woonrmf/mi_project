package com.project.backend.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultRequestDto {
	
	private Integer inspectionId;
	private Integer standardId;
	private ResultStatus status;
	private String memo;
}
