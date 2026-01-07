package com.project.backend.machine;

import java.time.LocalDateTime;
import java.util.List;

import com.project.backend.inspection.Inspection;
import com.project.backend.repair.Repair;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "machine")
@Getter
@Setter
public class Machine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true, nullable = false)
	private String mCode;
	
	private String location;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MachineStatus status;
	
	private LocalDateTime installDate;
	
	@PrePersist
	public void prePersist() {
		this.installDate = LocalDateTime.now();
	}
	
	@OneToMany(mappedBy = "machine", cascade = CascadeType.REMOVE)
	private List<Inspection> inspectionList;
	
	@OneToMany(mappedBy = "machine", cascade = CascadeType.REMOVE)
	private List<Repair> repairList;
}
