package com.project.backend.user;

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
@Table(name = "users")
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, nullable = false)
	private String loginId;
	
	@Column(nullable = false)
	private String password;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private LocalDateTime creDate;
	
	@PrePersist
	public void prePersist() {
		this.creDate = LocalDateTime.now();
	}
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Inspection> inspectionList;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Repair> repairList;
}
