package com.project.backend.inspection;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionRepository extends JpaRepository<Inspection, Integer> {
	boolean existsByMachineIdAndStatusIn (Integer machineId, List<InspectionStatus> statues);
}
