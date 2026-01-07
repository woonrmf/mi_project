package com.project.backend.standard;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StandardResponseDto {
	
	private Integer id;
	private String name;
	private String memo;
	
	public static StandardResponseDto from(Standard standard) {
		return new StandardResponseDto(standard.getId(), standard.getName(), standard.getMemo());
	}
}
