package com.project.backend.result;

import java.util.List;

import com.project.backend.inspection.Inspection;
import com.project.backend.repair.Repair;
import com.project.backend.standard.Standard;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "result")
@Getter
@Setter
public class Result {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ResultStatus status;
	
	private String memo;
	
	@ManyToOne
	private Inspection inspection;
	
	@ManyToOne
	private Standard standard;
	
	@OneToMany(mappedBy = "result", cascade = CascadeType.REMOVE)
	private List<Repair> repairList;
}
