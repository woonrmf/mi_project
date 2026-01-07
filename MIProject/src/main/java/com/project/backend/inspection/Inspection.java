package com.project.backend.inspection;

import java.time.LocalDateTime;
import java.util.List;

import com.project.backend.machine.Machine;
import com.project.backend.result.Result;
import com.project.backend.user.User;

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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "inspection")
@Getter
@Setter
public class Inspection {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String memo;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private InspectionStatus status;
	
	private LocalDateTime inspectionDate;
	
	@PrePersist
	public void prePersist() {
		this.inspectionDate = LocalDateTime.now();
	}
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Machine machine;
	
	@OneToMany(mappedBy = "inspection", cascade = CascadeType.REMOVE)
	private List<Result> resultList;
}
