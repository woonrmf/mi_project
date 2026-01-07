package com.project.backend.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultRequestDto {
	
	private ResultStatus status;
	private String memo;
}
