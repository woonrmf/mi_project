package com.project.backend.repair;

import java.time.LocalDateTime;

import com.project.backend.machine.Machine;
import com.project.backend.result.Result;
import com.project.backend.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "repair")
@Getter
@Setter
public class Repair {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String memo;
	
	private RepairStatus status;
	
	private LocalDateTime repairDate;
	
	@PrePersist
	public void prePersist() {
		this.repairDate = LocalDateTime.now();
	}
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Machine machine;
	
	@ManyToOne
	private Result result;
}
