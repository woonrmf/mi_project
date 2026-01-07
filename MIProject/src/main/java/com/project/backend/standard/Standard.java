package com.project.backend.standard;

import java.util.List;

import com.project.backend.result.Result;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "standard")
@Getter
@Setter
public class Standard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String memo;
	
	@OneToMany(mappedBy = "standard", cascade = CascadeType.REMOVE)
	private List<Result> resultList;
}
