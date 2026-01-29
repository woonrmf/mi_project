package com.project.backend.result;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Integer> {
	
	List<Result> findByInspectionId(Integer inspectionId);
	Optional<Result> findByInspectionIdAndStandardId(Integer inspectionId, Integer standardId);
}
